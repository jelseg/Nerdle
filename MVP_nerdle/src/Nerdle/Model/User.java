package Nerdle.Model;

import java.io.*;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.*;

public class User {

    private static String SAVEGAME_FOLDER = "resources" + File.separator + "other" + File.separator + "users" +
            File.separator;
    private String name;

    private Map<Difficulty,Integer> scores;
    private int totScore;

    private class ScoreRecord{

        private int answerId;
        private LocalDate date;
        private int nAttempts;
        private Difficulty difficulty;

        public ScoreRecord(String recordString) throws NerdleException{
            //align with toString method
            String[] splitted = recordString.split(";");

            if (splitted.length != 4){
                throw new NerdleException("Could not parse \"" + recordString + "\" as an entry. It should contain 4" +
                        "elements, splitted by ;");
            }

            try {
                date = LocalDate.parse(splitted[0],Nerdle.DATE_FORMAT);
                answerId = Integer.parseInt(splitted[1]);
                nAttempts = Integer.parseInt(splitted[3]);
            }
            catch (DateTimeParseException exception) {
                throw new NerdleException("Could not parse \"" + recordString + "\" as an entry. " + splitted[0] +
                        " is not a date of format " + Nerdle.DATE_FORMAT.toString());
            }
            catch (NumberFormatException exception){
                throw new NerdleException("Could not parse \"" + recordString + "\" as an entry. ", exception);
            }

            for(Difficulty dif: Difficulty.values()){
                if (dif.toString().equals(splitted[2])){
                    difficulty = dif;
                }
            }
            if (difficulty == null){
                throw new NerdleException("Could not parse \"" + recordString + "\" as an entry. " + splitted[2]+
                        " is not a difficulty level");
            }

        }

        // nAttemps: if failed => should be -1
        public ScoreRecord(int answerId,int nAttempts,Difficulty difficulty){
            this.answerId = answerId;
            this.nAttempts = nAttempts;
            this.difficulty = difficulty;
            this.date = LocalDate.now();
        }

        @Override
        public String toString() {
            String dateString = date.format(Nerdle.DATE_FORMAT);
            return String.format("%s;%d;%s;%d",dateString,answerId,difficulty,nAttempts);
        }

        public int getScore(){
            if (nAttempts < 0){
                return 0;
            }
            return difficulty.getnTries() - nAttempts+1;
        }

        public Difficulty getDifficulty(){
            return difficulty;
        }
    }

    public User(String name){
        this.name = name;
        totScore = Integer.MIN_VALUE;

        File scoreFile = getScoreFile();

        //create new user if not exists
        if(! scoreFile.exists()){
            try(FileWriter writer = new FileWriter(scoreFile)){

            }
            catch(IOException exception){
                throw new NerdleException("Could not create a score file for the new user.",exception);
            }
        }
    }

    public int getScore(Difficulty difficulty) throws NerdleException{

        if(scores == null){
            loadScore();
        }

        return scores.get(difficulty);
    }

    //returns the total score
    public int getScore() throws NerdleException{
        if (totScore == Integer.MIN_VALUE){
            loadScore();
        }
        return totScore;
    }


    // reads the userfiles for the records and updates the scores and totScore
    private void loadScore() throws NerdleException{


        //temp code

        //initialise scores Map
        scores = new EnumMap<Difficulty, Integer>(Difficulty.class);
        for(Difficulty diff:Difficulty.values()){
            scores.put(diff,0);
        }

        try(BufferedReader reader = new BufferedReader(new FileReader(getScoreFile()))){
            String line;
            while ((line = reader.readLine()) != null){
                ScoreRecord record = new ScoreRecord(line);
                scores.replace(record.getDifficulty(),scores.get(record.getDifficulty()) + record.getScore());
            }
        } catch (IOException exception){
            scores = null;
            throw new NerdleException("Could not read scores for " + name,exception);
        }


        totScore = 1*scores.get(Difficulty.NORMAL);
    }

    private File getScoreFile(){
        return new File(SAVEGAME_FOLDER + "SCORES_" + name + ".txt");
    }
    File getLastGameFile(Difficulty difficulty){
        return new File(SAVEGAME_FOLDER + "GAME_" + name + "_" + difficulty + ".txt");
    }

    public static List<User> getAllUsers() throws NerdleException{
        File folder = new File(SAVEGAME_FOLDER);

        if(!folder.exists() || !folder.isDirectory()){
            throw new NerdleException("The folder \"" + SAVEGAME_FOLDER + "\" does not exist.");
        }

        List<User> users = new LinkedList<>();

        for(File file:folder.listFiles()){
            if (file.isFile()){
                String fileName = file.getName();

                if(fileName.startsWith("SCORES_")){
                    String userName = fileName.substring(7,fileName.length()-4);
                    User nextUser = new User(userName);
                    users.add(nextUser);
                }
            }
        }

        return users;
    }



    void saveResult(Answer answer, Difficulty difficulty,int nAttempts) throws NerdleException{
        ScoreRecord record = new ScoreRecord(answer.getId(),nAttempts<=difficulty.getnTries()?nAttempts:-1, difficulty);

        try(BufferedWriter writer = new BufferedWriter(new FileWriter(getScoreFile(),true))){
            writer.write(record.toString());
            writer.newLine();
        }
        catch (IOException exception){
            throw new NerdleException("Could not save your score",exception);
        }
    }


    public String getName() {
        return name;
    }
}
