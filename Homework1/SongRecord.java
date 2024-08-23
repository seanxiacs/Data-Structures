package Homework1;

            /**
             * SongRecord holds information about audio files and is used by the Playlist class to store information about a song.
             * @author Sean Xia, SBU ID 113181409, R30
             */

public class SongRecord implements Cloneable {
    private String title;
    private String artist;
    private int minutes;
    private int seconds;

    /**
     * Default constructor of the SongRecord class.
     */
    public SongRecord() {
    }

    /**
     * Overloaded constructor that sets all the information about the audio file, and makes sure the conditions are valid.
     * @param newTitle
     * @param newArtist
     * @param newMinutes
     * @param newSeconds
     * @throws InvalidMinutesException
     * @throws InvalidSecondsException
     */
    public SongRecord(String newTitle, String newArtist, int newMinutes, int newSeconds) throws InvalidMinutesException, InvalidSecondsException {
        if(newMinutes < 0) {
            throw new InvalidMinutesException("The length of a song cannot be negative.");
        }
        if((newSeconds < 0) || (newSeconds > 59)) {
            throw new InvalidSecondsException("The number of seconds cannot be negative or greater than 59.");
        }
        this.title = newTitle;
        this.artist = newArtist;
        this.minutes = newMinutes;
        this.seconds = newSeconds;
    }

    /**
     * This method sets the title.
     * @param newTitle
     */
    public void setTitle(String newTitle) {
        this.title = newTitle;
    }

    /**
     * This method sets the artist.
     * @param newArtist
     */
    public void setArtist(String newArtist) {
        this.artist = newArtist;
    }

    /**
     * This method sets the minutes.
     * @param newMinutes
     * @throws InvalidMinutesException
     */
    public void setMinutes(int newMinutes) throws InvalidMinutesException {
        if(newMinutes < 0) {
            throw new InvalidMinutesException("The length of a song cannot be negative.");
        }
        this.minutes = newMinutes;
    }

    /**
     * This method sets the seconds.
     * @param newSeconds
     * @throws InvalidSecondsException
     */
    public void setSeconds(int newSeconds) throws InvalidSecondsException {
        if((newSeconds < 0) || (newSeconds > 59)) {
            throw new InvalidSecondsException("The number of seconds cannot be negative or greater than 59.");
        }
        this.seconds = newSeconds;
    }

    /**
     * This method gets the title.
     * @return title
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * This method gets the artist.
     * @return artist
     */
    public String getArtist() {
        return this.artist;
    }

    /**
     * This method gets the minutes.
     * @return
     */
    public int getMinutes() {
        return this.minutes;
    }

    /**
     * This method gets the seconds.
     * @return
     */
    public int getSeconds() {
        return this.seconds;
    }

    /**
     * The SongRecord toString method returns information in SongRecord in an ordered fashion.
     * @return formattedString
     */
    @Override
    public String toString() {
        String formattedString = String.format("%-25s%-25s%d%-1s", title, artist, minutes, ":") + String.format("%2d", seconds).replaceAll("\\s", "0");

        return formattedString;
    }

    /**
     * This method clones the class and returns the cloned Object.
     * @return cloned
     * @throws CloneNotSupportedException
     */
    @Override
    public Object clone() throws CloneNotSupportedException {
        SongRecord cloned = new SongRecord(); //Instantiation done by super.clone so not required.

        try {
            cloned = (SongRecord)super.clone();
        }
        catch(CloneNotSupportedException e) {
            throw new RuntimeException("Cloneable was not implemented.");
        }

        return cloned;
    }

    /**
     * This method checks to make sure that the SongRecord objects are equal.
     * @param obj
     * @return ((this.title.equalsIgnoreCase(candidate.title)) && (this.artist.equalsIgnoreCase(candidate.artist)) && (this.minutes == candidate.minutes) && (this.seconds == candidate.seconds));
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof SongRecord) {
            SongRecord candidate = (SongRecord) obj;

            return ((this.title.equalsIgnoreCase(candidate.title)) && (this.artist.equalsIgnoreCase(candidate.artist)) && (this.minutes == candidate.minutes) && (this.seconds == candidate.seconds));
        }
        else {
            return false;
        }
    }
}
