package Nerdle.Model;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * class to open and save games played today
 */
class SavedGames {

    static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyyMMdd");
    static final String saveGamesFolder = System.getProperty("user.home") + File.separator + "kdg-Nerdle";

    private String saveGamesFile = saveGamesFolder + File.separator + "savedGames.txt";

    private String[] lastSavedGame;
    private String userDif;

    SavedGames(User user, Difficulty difficulty) throws NerdleException{

        lastSavedGame = new String[] {};
        userDif = user.getName() + "," + difficulty.toString() + ",";

        findInSavedGamesFile(user,difficulty);

    }

    private void findInSavedGamesFile(User user, Difficulty difficulty) throws NerdleException{


        //check if folders exists
        Path folderPath = Paths.get(saveGamesFolder);
        if(!Files.exists(folderPath)){
            try {
                Files.createDirectory(folderPath);
                System.out.println("created folder: " + folderPath);
            } catch (IOException exception){
                throw new NerdleException("Unable to find or create a folder for to save games",exception);
            }
        }

        //check if file exists
        boolean hasToCreateNewFile = true;
        File savedGamesFile = new File(saveGamesFile);
        if(savedGamesFile.exists()){
            try(BufferedReader reader = new BufferedReader(new FileReader(savedGamesFile))){
                String line = reader.readLine();
                //check if it is today's file, if it is read the file, else there hasn't been a game saved today
                if(line.equals(LocalDate.now().format(DATE_FORMAT))){
                    System.out.println("savedGames file is today's");
                    hasToCreateNewFile = false;
                    while ((line = reader.readLine()) != null){
                        //expects user,difficulty,guesses(; seperated)
                        String[] splitted = line.split(",");

                        if(splitted.length != 3){
                            throw new NerdleException("The file " + saveGamesFile + " is corrupted.");
                        }

                        // note will always keep the last one in memory -> can use append to save
                        if(splitted[0].equals(user.getName()) && splitted[1].equals(difficulty.toString())){
                            lastSavedGame = splitted[2].split(";");
                        }


                    }
                }
            }
            catch(IOException exception){
                throw new NerdleException("Unable to read the file with saved games.",exception);
            }


            if(hasToCreateNewFile){
                try(BufferedWriter writer = new BufferedWriter( new FileWriter(saveGamesFile,false))){
                    //write the date
                    writer.write(LocalDate.now().format(DATE_FORMAT));
                    writer.newLine();

                    //write all the

                }
                catch (IOException exception){
                    throw new NerdleException("Could not overwrite the save games file",exception);
                }
            }
        }
    }

    void saveGame(List<Combination> guesses){

        try(BufferedWriter writer = new BufferedWriter( new FileWriter(saveGamesFile,true))){
            //convert combination to string to be written
            StringBuilder builder = new StringBuilder(userDif);
            for (Combination combo: guesses){
                builder.append(combo.getCombinationString()).append(";");
            }
            if(builder.length() > 0){
                builder.deleteCharAt(builder.length() - 1);
            }
            System.out.println(builder.toString());
            writer.write(builder.toString());
            writer.newLine();

        }
        catch (IOException exception){
            throw new NerdleException("Could not append to the save games file",exception);
        }
    }

    String[] getLastSavedGame(){return lastSavedGame;}
}
