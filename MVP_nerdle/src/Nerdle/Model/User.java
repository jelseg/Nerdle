package Nerdle.Model;

import java.io.*;
import java.nio.file.Files;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.*;

/**
 * A User plus methods for loading and saving scores
 */
public class User {

    //private static final String SAVEGAME_FOLDER = "resources" + File.separator + "other" + File.separator + "users" +
            //File.separator;
    private static final String SAVEGAME_FOLDER = SavedGames.saveGamesFolder;
    private String name;

    /**
     * scores will be empty until the first time a method for getting scores is called
     */
    private Map<Difficulty,Integer> scores;
    /**
     * totScore is Integere.MIN_VALUE until the first time a method for getting scores is called
     */
    private int totScore;
    /**
     * avgAttempts is Double.MIN_VALUE until the first time a method for getting scores is called
     * is Double.MAX_VALUE if the user hasn't finished a game yet
     */
    private double avgAttempts; //note: is Integer.MAX_VALUE when the user hasn't finished any game

    /**
     * class to parse one line of the score file
     */
    private static class ScoreRecord{

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
        public int getnAttempts(){
            return nAttempts;
        }

        public Difficulty getDifficulty(){
            return difficulty;
        }
    }

    /**
     * note: when there is no user with the given name it will create a new users (based on score files)
     * @param name
     */
    public User(String name){
        this.name = name;
        totScore = Integer.MIN_VALUE;
        avgAttempts = Double.MIN_VALUE;

        File scoreFile = getScoreFile();

        //create new user if not exists
        if(! scoreFile.exists()){
            try(FileWriter writer = new FileWriter(scoreFile)){
                // creates empty scorefile
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

    public double getAvgAttempts() throws NerdleException{
        if (avgAttempts == Double.MIN_VALUE){
            loadScore();
        }
        return avgAttempts;
    }

    /**
     * reads the userfiles for the records and updates the scores and totScore
     * @throws NerdleException
     */
    private void loadScore() throws NerdleException{

        //initialise scores Map
        scores = new EnumMap<Difficulty, Integer>(Difficulty.class);
        for(Difficulty diff:Difficulty.values()){
            scores.put(diff,0);
        }

        //init value average calculation
        int totAttemps = 0;
        int totGames = 0;

        try(BufferedReader reader = new BufferedReader(new FileReader(getScoreFile()))){
            String line;
            while ((line = reader.readLine()) != null && !line.equals("")){
                ScoreRecord record = new ScoreRecord(line);
                scores.replace(record.getDifficulty(),scores.get(record.getDifficulty()) + record.getScore());
                if (record.getnAttempts() == -1){
                    // if lost -> nattemps == -1
                    totAttemps += record.getDifficulty().getnTries()+1;
                }else {
                    totAttemps += record.getnAttempts();
                }
                totGames++;
            }
        } catch (IOException exception){
            scores = null;
            throw new NerdleException("Could not read scores for " + name,exception);
        }

        //calculate total score based on weighted scores per difficulty
        totScore = 1*scores.get(Difficulty.NORMAL);

        //calculate average
        if(totGames == 0){
            //give a default value when the player hasn't finished a game yet
            avgAttempts = Double.MAX_VALUE;
        }
        else {
            avgAttempts = (double)totAttemps/totGames;
        }
    }

    /**
     * costructs the path where the score file should be based on the user's name
     * @return
     */
    private File getScoreFile(){
        return new File(SAVEGAME_FOLDER +File.separator+ "SCORES_" + name + ".txt");
    }

    /*
    /**
     *
     * @param difficulty
     * @return File object for the file containing the last saved game of the user (might not exist if he hasn't
     * saved a game yet on this difficulty)

    File getLastGameFile(Difficulty difficulty){
        return new File(SAVEGAME_FOLDER + "GAME_" + name + "_" + difficulty + ".txt");
    }
    */

    /**
     *
     * @return all users that have a score file
     * @throws NerdleException
     */
    public static List<User> getAllUsers() throws NerdleException{
        File folder = new File(SAVEGAME_FOLDER);

        if(!folder.exists() || !folder.isDirectory()){
            //throw new NerdleException("The folder \"" + SAVEGAME_FOLDER + "\" does not exist.");
            try {
                Files.createDirectory(folder.toPath());
            }
            catch (IOException exception){
                throw new NerdleException("The folder \"" + SAVEGAME_FOLDER + "\" does not exist and could not be created.");
            }
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


    /**
     *  adds a score record to the score file of the user
     * @param answer the id will be added to the score record
     * @param difficulty
     * @param nAttempts number of attempts needed to finish the game (take max+1 when lost)
     * @throws NerdleException
     */
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
