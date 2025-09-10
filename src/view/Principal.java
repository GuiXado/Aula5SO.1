package view;

import java.util.concurrent.Semaphore;
import controller.ThreadRequisicao;

public class Principal {

	public static void main(String[] args) {
		
		Semaphore mutex = new Semaphore(1);
		
		for (int id = 1; id < 22; id++) {
			ThreadRequisicao t = new ThreadRequisicao(id, mutex);
			t.start();
		}
		
	}
	
}
