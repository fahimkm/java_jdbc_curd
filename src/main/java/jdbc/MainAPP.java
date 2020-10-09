package jdbc;

public class MainAPP {

	public static void main(String [] args) {
		final String url ="jdbc:oracle:thin:@localhost:1521:orcl";
		final String user = "system";
		final String password = "oracle";
		JDBC j1 = new JDBC(url,user,password);
			//emp1 table has fields name(varchar) ID(number) Status(varchar)

		//JDBC.insert("Tiger",7,"Active");
			//retrieve data
		JDBC.retrieve("select *from emp1 where ID = '1'");
			//use queries for general create,delete,drop operations
		//JDBC.queries("delete from emp1 where  id = '4'");
	}	

}
