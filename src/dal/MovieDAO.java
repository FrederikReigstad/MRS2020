package dal;

import be.Movie;

import java.io.*;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.nio.file.Files;
import java.nio.file.Path;

public class MovieDAO implements IMovieDataAccess {

    private static final String MOVIES_FILE = "data/movie_titles.txt";
    private List<Movie> movies = new ArrayList<>();




    public List<Movie> getAllMovies() throws IOException {
       List<Movie> allMovies = new ArrayList<>();
        File file = new File(MOVIES_FILE);
        Scanner scanner = new Scanner(file);

        while (scanner.hasNextLine()){
            String movieData = scanner.nextLine();

            String[] splitMovieData = movieData.split(",");
            int id = Integer.parseInt(splitMovieData[0]);
            int year  = Integer.parseInt(splitMovieData[1]);
            String title = splitMovieData[2];
            Movie movie = new Movie(id, year, title);
            allMovies.add(movie);
        }
        return allMovies;

    }

    @Override
    public Movie createMovie(String title, int year) throws Exception {
        try(FileWriter writer = new FileWriter(MOVIES_FILE, true);
            BufferedWriter bw = new BufferedWriter(writer)) {
            int id = getAllMovies().get(getAllMovies().size() - 1).getId() + 1;
                    Movie m = new  Movie(id, year,title);
                    bw.append(id + ""  + title);
                    bw.newLine();
                    movies.add(m);
                return m;



        }


    }

    @Override
    public void updateMovie(Movie movie) throws Exception {

    }

    @Override
    public void deleteMovie(Movie movie) throws Exception {

    }
    public static void main(String[] args) throws Exception {
        MovieDAO movieDAO =  new  MovieDAO();

        Movie movie = movieDAO.createMovie("The Retun Of Anrnor",2024);

    }





    /*
    public List<Movie> getAllMovies() {
        List<Movie> allMovieList = new ArrayList<>();

        File moviesFile = new File(MOVIES_FILE);


        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(moviesFile))) {
            boolean hasLines = true;
            while (hasLines) {
                String line = bufferedReader.readLine();
                hasLines = (line != null);
                if (hasLines && !line.isBlank())
                {
                    String[] separatedLine = line.split(",");

                    int id = Integer.parseInt(separatedLine[0]);
                    int year = Integer.parseInt(separatedLine[1]);
                    String title = separatedLine[2];
                    if(separatedLine.length > 3)
                    {
                        for(int i = 3; i < separatedLine.length; i++)
                        {
                            title += "," + separatedLine[i];
                        }
                    }
                    Movie movie = new Movie(id, title, year);
                    allMovieList.add(movie);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return allMovieList;
    }
    */


}