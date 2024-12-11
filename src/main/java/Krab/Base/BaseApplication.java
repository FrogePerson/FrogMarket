package Krab.Base;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import Krab.Base.BaseConnection.AlterPassword;

@SpringBootApplication
public class BaseApplication {

	public static void main(String[] args) {
                //AlterPassword.alterPassword("1917");
		SpringApplication.run(BaseApplication.class, args);
                
	}

}
