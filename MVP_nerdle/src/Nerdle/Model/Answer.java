package Nerdle.Model;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Formatter;
import java.util.LinkedList;
import java.util.Random;

/**
 *  extension to the combination class including methods for generating and saving the daily answer.
 */
public class Answer extends Combination{

    //%d should be filled with length of the answer
    //static final String ALL_ANSWERS_FILE = "resources" + File.separator + "other" + File.separator + "answers"+
            //File.separator + "allAnswers_%d.txt";
    // id:answer
    static final String ALL_ANSWERS_FORMAT = "%d;%s%n";

    //replace %s by difficulty (=> one file per difficulty)
    private static final String ANSWER_FILE = "resources" + File.separator + "other" + File.separator + "answers"+
            File.separator + "currentAnswer_%s.txt";
    //difficulty;id;answer
    private static final String ANSWER_FORMAT = "%s;%012d;%s";
    //private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyyMMdd");
    private String currentAnswerfilepath = SavedGames.saveGamesFolder+File.separator + "CurrentAnswer.txt";

    private int id;

    /**
     *
     * @param difficulty
     * @throws NerdleException when it couldn't open or parse the current answer file or the all answer file.
     */
    public Answer (Difficulty difficulty) throws NerdleException{
        super(difficulty);
        // loading and checks to be added
        //addStringToCombo("40x3=120");

        if (! openCurrentAnswer()){
            String combinationStr = generateNewAnswer();
            saveCurrentAnswer(combinationStr);
        }
    }

    /**
     *
     * turns a string into equation characters. Used to parse an answer in the all answers file or current answer file
     * to a Combination/Answer object.
     *
     * @param s String representing the equation
     * @return true when all characters in the string s could be added to the answer
     */
    private boolean addStringToCombo(String s){

        boolean ok = true;

        //placeholder
        for (int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            if(!super.addToEnd(c)){
                ok = false;
            }
        }
        return ok;
    }

    /**
     *
     * chooses a new answer from the list of possible answer contain
     * it will turn this equation into a Combination
     *
     * @return a string representing the newly choosen answer
     * @throws NerdleException when the file with all possible answer couldn't be opened or when the choosen line
     * isn't formatted propperly
     */
    private String generateNewAnswer() throws NerdleException{

        //make list of all possible answers
        LinkedList<String> allAnswers = new LinkedList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(getAllAnswerFile(difficulty)))){
            String line;
            while ((line = in.readLine()) != null ){
                allAnswers.add(line);
            }
        }
        catch (IOException ioe){
            throw new NerdleException("A problem occurred trying to generate a new answer for today.",ioe);
        }

        Random rand = new Random();
        String choosenLine = allAnswers.get(rand.nextInt(allAnswers.size()));

        String[] splitted = choosenLine.split(";");
        if (splitted.length != 2){
            throw new NerdleException("The file with possible answers is not formatted properly." + choosenLine);
        }
        try {
            id = Integer.parseInt(splitted[0]);
        }
        catch (NumberFormatException nfe){
            throw new NerdleException("The file with possible answers is not formatted properly. The first element of " +
                    "each line should be an integer representing the id." + splitted[0]);
        }

        if(splitted[1].length() != difficulty.getComboLength()){
            throw new NerdleException("The file with possible answers is not formatted properly. Not all answers have " +
                    "the correct length" + splitted[1]);
        }

        addStringToCombo(splitted[1]);

        if(!isLegal()){
            throw new NerdleException("The file with possible answers is not formatted properly. One of the answers " +
                    "is not legal: " + splitted[1] );
        }

        return splitted[1];

    }

    /**
     * checks the current answer file. If it contains today's answer it will use this equation as combination
     *
     * @return true when the date in the current answer file is today (and the answer from this file is used)
     * @throws NerdleException the current answer file couldn't be opened or wasn't formatted properly
     */
    private boolean openCurrentAnswer() throws NerdleException {

        File answerFile = new File(currentAnswerfilepath);

        boolean foundAnswer = false;
        boolean createNewFile = true;
        String[] splittedLine = new String[]{};

        if(answerFile.exists()){
            try(BufferedReader reader = new BufferedReader(new FileReader(answerFile))) {
                String line = reader.readLine();
                if(line.equals(LocalDate.now().format(SavedGames.DATE_FORMAT))){
                    createNewFile = false;
                    while ((line = reader.readLine())!=null){
                        String[] currentsplitedRecord = line.split(";");
                        if (currentsplitedRecord.length != 3){
                            throw new NerdleException("The current answer file is not properly formatted.");
                        }
                        if(currentsplitedRecord[0].equals(difficulty.toString())){
                            foundAnswer = true;
                            splittedLine = currentsplitedRecord;
                        }
                    }
                }

            }
            catch (IOException exception){
                throw new NerdleException("We could not read the file with the current answers",exception);
            }
        }

        if (foundAnswer){
            try {
                id = Integer.parseInt(splittedLine[1]);
            }
            catch (NumberFormatException nfe){
                throw new NerdleException("The file with the current answer is not formatted properly. " +
                        "The second element should be an integer representing the id." + splittedLine[1]);
            }

            addStringToCombo(splittedLine[2]);

            if (! isLegal()){
                throw new NerdleException("The file with the current answer is not formatted properly. " +
                        "The combination is not legal." + splittedLine[2]);
            }
        }

        if (createNewFile){
            try(BufferedWriter writer = new BufferedWriter(new FileWriter(answerFile))){
                writer.write(LocalDate.now().format(SavedGames.DATE_FORMAT));
                writer.newLine();
            }
            catch (IOException exception){
                throw new NerdleException("Could not create or overwrite the current answer file",exception);
            }
        }

        /*

        File answerFile = new File(String.format(ANSWER_FILE,difficulty));

        String[] splittedLine;
        if(answerFile.exists()){
            try(FileReader reader = new FileReader(answerFile)){
                int buffersize = 10 + 12 + 2 + difficulty.getComboLength();
                final char[] buffer = new char[buffersize];
                reader.read(buffer);
                splittedLine = new String(buffer).trim().split(";");
            }
            catch(IOException ioe){
                throw new NerdleException("A problem occurred when trying to load the current answer.",ioe);
            }

            if(splittedLine.length != 3){
                throw new NerdleException("The current answer file is not properly formatted.");
            }

            String todayStr = LocalDate.now().format(Nerdle.DATE_FORMAT);

            if (todayStr.equals(splittedLine[0])) {

                try {
                    id = Integer.parseInt(splittedLine[1]);
                }
                catch (NumberFormatException nfe){
                    throw new NerdleException("The file with the current answer is not formatted properly. " +
                            "The second element should be an integer representing the id." + splittedLine[1]);
                }

                addStringToCombo(splittedLine[2]);

                if (! isLegal()){
                    throw new NerdleException("The file with the current answer is not formatted properly. " +
                            "The combination is not legal." + splittedLine[2]);
                }

                return true;
            }
            else {
                return false;
            }

        }
        else {
            return false;
        }

         */

        return foundAnswer;

    }

    /**
     *
     * writes the string representing an equationt to the current answer file.
     *
     * @param conbinationStr String representing the equation. We use the exact string found in the all answers file
     * @throws NerdleException when an IOException happens while overwriting the current answer file
     */
    private void saveCurrentAnswer(String conbinationStr) throws NerdleException {
        File answerFile = new File(currentAnswerfilepath);

        try(BufferedWriter writer = new BufferedWriter(new FileWriter(answerFile,true))){
            //formater.format(ANSWER_FORMAT,LocalDate.now().format(Nerdle.DATE_FORMAT),id,conbinationStr);
            writer.write(String.format(ANSWER_FORMAT,difficulty,id,conbinationStr));
            writer.newLine();
        }
        catch (IOException ioe){
            throw new NerdleException("A problem occurred trying to save today's answer.",ioe);
        }
    }

    /**
     *
     * @return the id of the answer as found in the all answers file
     */
    public int getId() {
        return id;
    }

    /**
     * generates the path to the text file containing all possible answers for a difficulty
     *
     * @param difficulty the difficulty for which you want the file
     * @return the path to the file with all possible answers
     */
    static String getAllAnswerFile(Difficulty difficulty){
        return "resources" + File.separator + "other" + File.separator + "answers"+
                File.separator + "allAnswers_" + difficulty.toString() + ".txt";
    }
}
