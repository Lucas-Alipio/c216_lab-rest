package br.inatel.labs.labrest.server.controler;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloControler {
	
	@GetMapping
	public MyMassage processarGetHello() {
		MyMassage msg = new MyMassage();
		msg.setInfo("Olá Mundo REST");
		return msg;
	}

}
