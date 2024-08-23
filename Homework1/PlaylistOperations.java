package Homework1;

/**
 * PlaylistOperations manipulates the user's inputs to perform operations involving a playlist.
 * @author Sean Xia, SBU ID 113181409, R30
 */

import java.util.InputMismatchException;
import java.util.Scanner;

public class PlaylistOperations {
    private static final Scanner input = new Scanner(System.in);
    private static final Scanner inputString = new Scanner(System.in);
    private static String menuOption;
    private static Playlist list = new Playlist();

    /**
     * This method runs until the user quits, performing actions that are provided in a menu option.
     * @param args
     * @throws InvalidMinutesException
     * @throws InvalidSecondsException
     * @throws FullPlaylistException
     * @throws CloneNotSupportedException
     */
    public static void main(String[] args) throws InvalidMinutesException, InvalidSecondsException, FullPlaylistException, CloneNotSupportedException {
        do {
            try {
                displayMenu();
                System.out.print("Select a menu option: ");
                menuOption = input.next().toLowerCase();
                System.out.println();

                switch (menuOption) {
                    case "a":
                        System.out.print("Enter the song title: ");
                        String newTitle = inputString.nextLine();
                        System.out.print("Enter the song artist: ");
                        String newArtist = inputString.nextLine();
                        System.out.print("Enter the song length (minutes): ");
                        int newMinutes = input.nextInt();
                        System.out.print("Enter the song length (seconds): ");
                        int newSeconds = input.nextInt();
                        System.out.print("Enter the position: ");
                        int addPosition = input.nextInt();
                        System.out.println();

                        try {
                            SongRecord addSong = new SongRecord(newTitle, newArtist, newMinutes, newSeconds);
                            list.addSong(addSong, addPosition);
                            System.out.println("Song Added: " + newTitle + " By " + newArtist + "\n");
                        }
                        catch(IllegalArgumentException e) {
                            System.out.println("Invalid position for adding the new song.\n");
                        }
                        catch(InvalidMinutesException e) {
                            System.out.println("Invalid Song Length.\n");
                        }
                        catch(InvalidSecondsException e) {
                            System.out.println("Invalid Song Length.\n");
                        }
                        break;
                    case "b":
                        System.out.print("Enter the artist: ");
                        String searchArtist = inputString.nextLine();
                        System.out.println();

                        System.out.println(list.getSongsByArtist(list, searchArtist));
                        break;
                    case "g":
                        System.out.print("Enter the position: ");
                        //Tried to use pos again but it forced me to reuse or whatever? I thought they were not in the same scope.
                        int getPosition = input.nextInt();
                        System.out.println();

                        Playlist getSongPlaylist = new Playlist();
                        getSongPlaylist.addSong(list.getSong(getPosition), 1);

                        System.out.println(getSongPlaylist + "\n");
                        break;
                    case "r":
                        System.out.print("Enter the position: ");
                        int removePosition = input.nextInt();
                        System.out.println();

                        try {
                            list.removeSong(removePosition);
                            System.out.println("Song Removed at position " + removePosition + "\n");
                        }
                        catch(IllegalArgumentException e) {
                            System.out.println("No song at position " + removePosition + " to remove.\n");
                        }
                        break;
                    case "p":
                        list.printAllSongs();
                        break;
                    case "s":
                        System.out.println("There are " + list.size() + " song(s) in the current playlist.\n");
                        break;
                    case "q":
                        System.out.println("Program terminating normally...");
                        break;
                    default:
                        System.out.println("You did not select a valid menu option. Please try again.\n");
                }
            }
            catch(IllegalArgumentException e) {
                System.out.println("Please enter a position that is within the valid range next time.\n");
            }
            catch(InputMismatchException e) {
                System.out.println("Please enter an argument of the right type.\n");
            }
            catch(FullPlaylistException e) {
                System.out.println("Please do not try to add a song into a playlist that is full.\n");
            }
            catch(InvalidMinutesException e) {
                System.out.println("Please enter a non-negative minutes for the song.\n");
            }
            catch(InvalidSecondsException e) {
                System.out.println("Please keep the seconds in the range of 0 to 59 (inclusive).\n");
            }
            catch(CloneNotSupportedException e) {
                System.out.println("Cloneable does not seem to be implemented.");
            }
            catch(NullPointerException e) {
                System.out.println("There seems to be a null-pointer exception occurring,");
            }
            catch(Exception e) {
                System.out.println("There seems to be an error that was not entirely accounted for..\n");
            }
        }
        while(menuOption.compareTo("q") != 0);

        input.close();
        inputString.close();
    }

    /**
     * This method prints the menu for the user to see the operations that can be performed.
     */
    public static void displayMenu() {
        String menu = "Options Menu: \n" +
                "A) Add Song \n" +
                "B) Print Songs by Artist \n" +
                "G) Get Song \n" +
                "R) Remove Song \n" +
                "P) Print All Songs \n" +
                "S) Size \n" +
                "Q) Quit \n";
        System.out.println(menu);
    }
}