package Nerdle.Model;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Formatter;
import java.util.LinkedList;
import java.util.Random;

public class Answer extends Combination{

    //%d should be filled with length of the answer
    static final String ALL_ANSWERS_FILE = "resources" + File.separator + "other" + File.separator + "answers"+
            File.separator + "allAnswers_%d.txt";
    // id:answer
    static final String ALL_ANSWERS_FORMAT = "%012d;%s%n";

    //replace %s by difficulty (=> one file per difficulty)
    private static final String ANSWER_FILE = "resources" + File.separator + "other" + File.separator + "answers"+
            File.separator + "currentAnswer_%s.txt";
    //date;id;answer
    private static final String ANSWER_FORMAT = "%s;%012d;%s";
    //private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyyMMdd");

    private int id;

    public Answer (Difficulty difficulty) throws NerdleException{
        super(difficulty);
        // loading and checks to be added
        //addStringToCombo("40x3=120");

        if (! openCurrentAnswer()){
            String combinationStr = generateNewAnswer();
            saveCurrentAnswer(combinationStr);
        }
    }

    //returns true if string s is a valid equation and could be added as a combination
    private boolean addStringToCombo(String s){
        //placeholder
        for (int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            super.addToEnd(c);
        }
        return true;
    }

    //returns the string version of the answer combination
    private String generateNewAnswer() throws NerdleException{

        //make list of all possible answers
        LinkedList<String> allAnswers = new LinkedList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(String.format(ALL_ANSWERS_FILE,difficulty.getComboLength())))){
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

    //returns true when there is a current answer
    private boolean openCurrentAnswer() throws NerdleException {

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

    }

    private void saveCurrentAnswer(String conbinationStr) throws NerdleException {
        File answerFile = new File(String.format(ANSWER_FILE,difficulty));

        try(Formatter formater = new Formatter(answerFile)){
            formater.format(ANSWER_FORMAT,LocalDate.now().format(Nerdle.DATE_FORMAT),id,conbinationStr);
        }
        catch (IOException ioe){
            throw new NerdleException("A problem occurred trying to save today's answer.",ioe);
        }
    }

    public int getId() {
        return id;
    }
}
