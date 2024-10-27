import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Scanner;
import java.util.Comparator;

public class MusicManager {
    LinkedList<Song> playlist = new LinkedList<>();
    ListIterator<Song> iterator = playlist.listIterator();
    Song current = null;

    public void nextSong() {
        if (iterator.hasNext()) {
            current = iterator.next();
            System.out.println("Playing: " + current);
        } else {
            System.out.println("End of the playlist.");
        }
    }

    public void previousSong() {
        if (iterator.hasPrevious()) {
            current = iterator.previous();
            System.out.println("Playing: " + current);
        } else {
            System.out.println("Start of the playlist.");
        }
    }

    public void sortPlaylist(int criterion) {
        if (criterion == 1) {
            playlist.sort(Comparator.comparing(s -> s.title));
        } else if (criterion == 2) {
            playlist.sort(Comparator.comparing(s -> s.artist));
        }
        iterator = playlist.listIterator();
        System.out.println("Playlist sorted.");
    }

    public void playSong() {
        if (current != null) {
            System.out.println("Now playing: " + current);
        } else {
            System.out.println("No song selected.");
        }
    }

    public void addSong(String title, String artist, String album, int duration, int position) {
        Song newSong = new Song(title, artist, album, duration);
        if (position <= 0) {
            playlist.addFirst(newSong);
        } else if (position >= playlist.size()) {
            playlist.addLast(newSong);
        } else {
            playlist.add(position, newSong);
        }
        iterator = playlist.listIterator();
        System.out.println("Song added.");
    }

    public void removeSong(String title) {
        playlist.removeIf(s -> s.title.equalsIgnoreCase(title));
        iterator = playlist.listIterator();
        System.out.println("Song removed.");
    }

    public void listSongs() {
        if (playlist.isEmpty()) {
            System.out.println("The playlist is empty.");
        } else {
            for (Song s : playlist) {
                System.out.println(s);
            }
        }
    }

    public void menu() {
        Scanner scanner = new Scanner(System.in);
        int option;
        do {
            System.out.println("\nWelcome to your Music Manager!");
            System.out.println("1. Next song");
            System.out.println("2. Previous song");
            System.out.println("3. Sort playlist");
            System.out.println("4. Play song");
            System.out.println("5. Add song");
            System.out.println("6. Remove song");
            System.out.println("7. List songs");
            System.out.println("8. Exit");
            System.out.print("Enter your choice: ");
            option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    nextSong();
                    break;
                case 2:
                    previousSong();
                    break;
                case 3:
                    System.out.println("Choose sorting criterion: 1. Title, 2. Artist");
                    int criterion = scanner.nextInt();
                    sortPlaylist(criterion);
                    break;
                case 4:
                    playSong();
                    break;
                case 5:
                    System.out.print("Title: ");
                    String title = scanner.nextLine();
                    System.out.print("Artist: ");
                    String artist = scanner.nextLine();
                    System.out.print("Album: ");
                    String album = scanner.nextLine();
                    System.out.print("Duration (seconds): ");
                    int duration = scanner.nextInt();
                    System.out.print("Position (0 for start, -1 for end): ");
                    int position = scanner.nextInt();
                    addSong(title, artist, album, duration, position);
                    break;
                case 6:
                    System.out.print("Enter the title of the song to remove: ");
                    String titleToRemove = scanner.nextLine();
                    removeSong(titleToRemove);
                    break;
                case 7:
                    listSongs();
                    break;
                case 8:
                    System.out.println("Exiting the program...");
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        } while (option != 8);
        scanner.close();
    }
}