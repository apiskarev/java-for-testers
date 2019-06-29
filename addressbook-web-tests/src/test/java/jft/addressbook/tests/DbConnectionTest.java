package jft.addressbook.tests;

import jft.addressbook.model.GroupData;
import jft.addressbook.model.Groups;
import org.testng.annotations.Test;

import java.sql.*;

public class DbConnectionTest {

    @Test
    public void testConnection(){
        Connection conn;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/addressbook?user=root&password=&serverTimezone=UTC");
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("select group_id.group_name.group_header.group_footer from group_list");
            Groups groups= new Groups();
            while (rs.next()){
                groups.add(new GroupData().withId(rs.getInt("group_id")).withName(rs.getString("group_name"))
                        .withHeader(rs.getString("group_header")));
            }
            rs.close();
            st.close();
            conn.close();
            System.out.println(groups);
        } catch (SQLException e){
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        }
    }
}
