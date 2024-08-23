package Homework1;

/**
 * Playlist holds information keeps an array of SongRecord files to create a playlist of songs that can be edited in the Playlist Operations class..
 * @author Sean Xia, SBU ID 113181409, R30
 */

public class Playlist {
    private static final int MAX_SONGS = 50;
    private SongRecord[] songPlaylist = new SongRecord[MAX_SONGS];
    private int songs_currently_in_playlist;

    /**
     * Default constructor of the Playlist class.
     */
    public Playlist() {
    }

    /**
     * This method clones the class and returns the cloned Object.
     * @return copy
     * @throws CloneNotSupportedException
     */
    @Override
    public Object clone() throws CloneNotSupportedException {
        Playlist copy = new Playlist();

        for(int i = 0; i < songs_currently_in_playlist; i++) {
            copy.songPlaylist[i] = (SongRecord)songPlaylist[i].clone();
        }

        copy.songs_currently_in_playlist = songs_currently_in_playlist;

        return copy;
    }

    /**
     * This method checks to make sure that the Playlist objects are equal.
     * @param obj
     * @return True or False based on if the entered object is equal.
     */
    public boolean equals(Object obj) {
        if(obj instanceof Playlist) {
            //boolean flag = true;
            Playlist candidate = (Playlist)obj;

            for(int i = 0; i < songs_currently_in_playlist; i++) {
                if(!songPlaylist[i].equals(candidate.songPlaylist[i])) {
                    return false;
                }
            }

            if(songs_currently_in_playlist != candidate.songs_currently_in_playlist) {
                return false;
            }
            else {
                return true;
            }
        }

        else {
            return false;
        }
    }

    /**
     * Returns the current size of the playlist.
     * @return songs_currently_in_playlist
     */
    public int size() {
        return songs_currently_in_playlist;
    }

    /**
     * This method adds the song to the playlist after checking to see if it is possible to add the song into the playlist.
     * @param song
     * @param position
     * @throws FullPlaylistException
     */
    public void addSong(SongRecord song, int position) throws FullPlaylistException {
        if((position < 1) || (position > MAX_SONGS + 1) || (position > songs_currently_in_playlist + 1)) {
            throw new IllegalArgumentException("The position is not within a valid range.");
        }
        if(songs_currently_in_playlist == MAX_SONGS) {
            throw new FullPlaylistException("Cannot add song because the playlist is full.");
        }

        if(position <= songs_currently_in_playlist) {
            for(int i = position - 1; i < songs_currently_in_playlist; i++) {
                songPlaylist[i + 1] = songPlaylist[i];
            }
        }
        songPlaylist[position - 1] = song;

        songs_currently_in_playlist++;
    }

    /**
     * This method removes the song from the playlist after checking to make sure we are removing from a valid position.
     * @param position
     */
    public void removeSong(int position) {
        if((position < 1) || (position > songs_currently_in_playlist)) {
            throw new IllegalArgumentException("No song at position " + position + "to remove.");
        }

        if(position < songs_currently_in_playlist) {
            for(int i = position - 1; i < songs_currently_in_playlist - 1; i++) {
                songPlaylist[i] = songPlaylist[i + 1];
            }
        }

        songPlaylist[songs_currently_in_playlist - 1] = null;
        songs_currently_in_playlist--;
    }

    /**
     * This method gets the song from a certain position after checking to make sure the position is valid.
     * @param position
     * @return songPLaylist[position - 1]
     */
    public SongRecord getSong(int position) {
        if((position < 1) || (position > songs_currently_in_playlist)) {
            throw new IllegalArgumentException("The position is not within a valid range to get the song.");
        }
        return songPlaylist[position - 1];
    }

    /**
     * This method prints all of the songs in the playlist in an ordered table.
     */
    public void printAllSongs() {
        System.out.println(toString());
    }

    /**
     * This method creates a new Playlist object with all of the songs by a certain artist.
     * @param originalList
     * @param artist
     * @return artistPlaylist
     * @throws InvalidMinutesException
     * @throws InvalidSecondsException
     * @throws FullPlaylistException
     * @throws CloneNotSupportedException
     */
    public static Playlist getSongsByArtist(Playlist originalList, String artist) throws InvalidMinutesException, InvalidSecondsException, FullPlaylistException, CloneNotSupportedException {
        if((originalList == null) || (artist == null)) {
            return null;
        }

        Playlist artistPlaylist = new Playlist();
        int pos = 1;

        for(int i = 0; i < originalList.songs_currently_in_playlist; i++) {
            if(artist.equalsIgnoreCase(originalList.songPlaylist[i].getArtist())) {
                SongRecord referenceSong = (SongRecord)originalList.songPlaylist[i].clone();
                artistPlaylist.addSong(new SongRecord(referenceSong.getTitle(), referenceSong.getArtist(), referenceSong.getMinutes(), referenceSong.getSeconds()), pos);
                pos++;
            }
        }

        return artistPlaylist;
    }

    /**
     * The Playlist toString method returns information in Playlist in an ordered fashion.
     * @return formattedString
     */
    @Override
    public String toString() {
        String table = "";
        table += String.format("%-8s%-25s%-25s%-6s\n", "Song#", "Title", "Artist", "Length");

        table += new String(new char[64]).replace("\0", "-");
        table += "\n";

        for(int i = 0; i < songs_currently_in_playlist; i++) {
            table += String.format("%-8d", i + 1) + songPlaylist[i] + "\n";
        }

        return table;
    }
}
