package Homework1;

public class FullPlaylistException extends Exception {
    public FullPlaylistException() {
        System.out.println("Cannot perform operation because the playlist is full.");
    }

    public FullPlaylistException(String s) {
        System.out.println(s);
    }
}
