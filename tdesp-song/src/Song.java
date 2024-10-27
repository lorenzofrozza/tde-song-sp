class Song {
    String title;
    String artist;
    String album;
    int duration; // duration in seconds

    Song(String title, String artist, String album, int duration) {
        this.title = title;
        this.artist = artist;
        this.album = album;
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "Title: " + title + ", Artist: " + artist +
                ", Album: " + album + ", Duration: " + duration + "s";
    }
}