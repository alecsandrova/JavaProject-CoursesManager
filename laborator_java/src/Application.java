import java.beans.ExceptionListener;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class Application {
    private static final String COMMA_DELIMITER = ",";
    private static Application single_instance = null;
    private List<User> userList = new ArrayList<>();

    public User currentUser = null;

    static Application getInstance() {
        if ( single_instance == null) {
            single_instance = new Application();
        }
        return  single_instance;
    }

    private Application() {
         /* HardcodatDataManager dataManager = new HardcodatDataManager();
        Random r = new Random();
        var studenti = dataManager.dataSetOfStudent;
        var profesori = dataManager.dataSetOfProfesor;
        this.userList.add(new User("aaa", "aaa", new StudentStrategy( studenti[r.nextInt(studenti.length)])));
        this.userList.add(new User("bbb", "aaa", new TeacherStrategy( profesori[r.nextInt(profesori.length)])));
        this.userList.add(new User("ccc", "ccc", new StudentStrategy( studenti[r.nextInt(studenti.length)])));
        this.userList.add(new User("ddd", "ddd", new TeacherStrategy( profesori[r.nextInt(profesori.length)])));
        this.userList.add(new User("eee", "eee", new StudentStrategy( studenti[r.nextInt(studenti.length)])));
        try {
            FileOutputStream fos = new FileOutputStream("users.xml");
            XMLEncoder encoder = new XMLEncoder(fos);
            encoder.setExceptionListener(new ExceptionListener() {
                @Override
                public void exceptionThrown(Exception e) {
                    System.out.println("Exception:" + e.toString());
                }
            });
            encoder.writeObject(userList);
            encoder.close();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } */
        this.initUsers();
    }

    private void initUsers() {
//        try (FileInputStream fis = new FileInputStream("users.xml")) {
//            XMLDecoder decoder = new XMLDecoder(fis);
//            this.userList = (ArrayList<User>)decoder.readObject();
//            decoder.close();
//            fis.close();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        try (BufferedReader br = new BufferedReader(new FileReader("users.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(COMMA_DELIMITER);
                UserAccountType account = UserAccountType.valueOf(values[4]);
                User user = new User(values[0],values[1],values[2],values[3],account);
                this.userList.add(user);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void login(User user) throws Exception {
        int index = userList.indexOf(user);
        if ( index != -1 ) {
            Application.getInstance().currentUser = userList.get(index);
        } else {
            throw new Exception("Username sau parola este gresita!");
        }
    }

    public void addUser(User user) throws  Exception {
        userList.add(user);
        addLine(user);

    }

    public void addLine(User user) throws Exception {
        String line =user.firstName.toString() + "," + user.lastName.toString() + "," + user.userName.toString() + "," + user.password.toString() +  "," + user.accountType.toString() + ",";
        try (FileWriter writer = new FileWriter("users.csv", true);
             BufferedWriter bw = new BufferedWriter(writer)) {

            bw.newLine();
            bw.write(line);
            bw.close();

        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }
        }




}
