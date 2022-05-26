package entity;

import org.mindrot.jbcrypt.BCrypt;
import pl.coderslab.mysql.Workshop2.DbUtil;

import java.sql.*;
import java.util.Scanner;


public class UserDao {
    private static final String Create_User_Query = "INSERT INTO users(email, username, password) VALUES (?, ?, ?)";
    private static final String Update_User_Query = "UPDATE users t SET t.email =?, t.username=?, t.password=? WHERE t.id = ?";
    private static final String Select_User_Query = "SELECT * FROM users WHERE id=(?);";
    private static final String Delete_User_Query = "DELETE FROM users WHERE id = (?)";
    private static final String Update_User_Query = "UPDATE users t SET t.email=?, t.username=?, t.password=? WHERE t.id = ?";
    private static final String Select_User_Query = "SELECT id, email, username, password FROM users WHERE id = ?";
    private static final String Delete_User_Query = "DELETE FROM users WHERE id = ?";
    private static final String SelectAll_User_Query = "SELECT * FROM users;";

    public String hashPassword(String password) {
        public static String hashPassword(String password) {
            return BCrypt.hashpw(password, BCrypt.gensalt());
        }

        public User create(User user) {
            try (Connection conn = DbUtil.getConnection()) {
                public static User create(User user) {
                    try (Connection connection = DbUtil.getConnection()) {
                        PreparedStatement statement =
                                conn.prepareStatement(Create_User_Query, Statement.RETURN_GENERATED_KEYS);
                        statement.setString(1, user.getUserName());
                        statement.setString(2, user.getEmail());
                        connection.prepareStatement(Create_User_Query, Statement.RETURN_GENERATED_KEYS);
                        statement.setString(1, user.getEmail());
                        statement.setString(2, user.getUserName());
                        statement.setString(3, hashPassword(user.getPassword()));
                        statement.executeUpdate();
                        ResultSet resultSet = statement.getGeneratedKeys();
                        @@ -36,13 +36,107 @@ public User create(User user) {
                        }
                    }




                    //    public static User read(int UserId) {
//        try (Connection connection = DbUtil.getConnection()) {
//            PreparedStatement statement = connection.prepareStatement(Select_User_Query);
//            statement.setInt(1, UserId);
//            ResultSet rs = statement.executeQuery();
//            if (rs.next()) {
//                int id = rs.getInt("id");
//                String email = rs.getString("email");
//                String username = rs.getString("username");
//                //String password = rs.getString("password");
//                System.out.println("id = " + id + ", email = " + email + ", username = " + username);
//            } else {
//                System.out.println("null");
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        return null;
                    public static User read(int userId) {
                        try (Connection connection = DbUtil.getConnection()) {
                            PreparedStatement statement = connection.prepareStatement(Select_User_Query);
                            statement.setInt(1, userId);
                            ResultSet resultSet = statement.executeQuery();
                            if (resultSet.next()) {
                                User user = new User();
                                user.setId(resultSet.getInt("id"));
                                user.setEmail(resultSet.getString("email"));
                                user.setUserName(resultSet.getString("username"));
                                user.setPassword(resultSet.getString("password"));
                                return user;
                            }
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        return null;
                    }

                    //    public static void update() {
//        read(4);
//        try (Connection connection = DbUtil.getConnection()) {
//            PreparedStatement statement = connection.prepareStatement(Select_User_Query);
//            ResultSet resultSet = statement.executeQuery();
//            statement.setString(1, User.getUserName());
//            statement.setString(2, User.getEmail());
//            statement.setString(3, hashPassword(User.getPassword()));
//            if (resultSet.next()) {
//                User user = new User();
//                //user.setId(resultSet.getInt("id"));
//                user.setUserName(resultSet.getString("username"));
//                user.setEmail(resultSet.getString("email"));
//                user.setPassword(resultSet.getString("password"));
//                return user;
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
                    public void update(User user) {
                        // read(5);
                        try (Connection connection = DbUtil.getConnection()) {
                            PreparedStatement statement = connection.prepareStatement(Update_User_Query);
                            statement.setString(1, user.getEmail());
                            statement.setString(2, user.getUserName());
                            statement.setString(3, this.hashPassword(user.getPassword()));
                            statement.setInt(4, user.getId());
                            statement.executeUpdate();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }

                    public static void delete() {
                        Scanner scanner = new Scanner(System.in);
                        System.out.println("Co wywalamy dzisiaj?");
                        int userId = scanner.nextInt();
                        System.out.println(userId);
                        try (Connection connection = DbUtil.getConnection()) {
                            PreparedStatement statement = connection.prepareStatement(Delete_User_Query);
                            statement.setInt(1, userId);
                            statement.executeUpdate();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }

                    public static void findAll() {
                        try (Connection connection = DbUtil.getConnection()) {
                            PreparedStatement statement = connection.prepareStatement(SelectAll_User_Query);
                            ResultSet resultSet = statement.executeQuery();
                            while (resultSet.next()) {
                                User user = new User();
                                user.setId(resultSet.getInt("id"));
                                user.setEmail(resultSet.getString("email"));
                                user.setUserName(resultSet.getString("username"));
                                user.setPassword(resultSet.getString("password"));
                                System.out.println(user);
                            }
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                }