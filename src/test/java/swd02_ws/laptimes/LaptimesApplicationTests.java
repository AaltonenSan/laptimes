package swd02_ws.laptimes;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import swd02_ws.laptimes.web.LaptimeController;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class LaptimesApplicationTests {

	@Autowired
	private LaptimeController lcontroller;
	
	@Test
	void contextLoads() throws Exception {
		assertNotNull(lcontroller);
	}

}
