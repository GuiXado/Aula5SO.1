package controller;

import java.util.concurrent.Semaphore;

public class ThreadRequisicao extends Thread {

	private int id;
	private Semaphore mutex;
	private int tempo;
	private int quant, inicio = 0;
	
	public ThreadRequisicao(int id, Semaphore mutex) {
		this.id = id;
		this.mutex = mutex;
	}
	
	public void run() {
		if (id % 3 == 1) {
			quant = 2;
		} else if (id % 3 == 0 || id % 3 == 2) {
			quant = 3;
		}
		while (inicio < quant) {
			calculo();
			try {
				mutex.acquire();
				transacao();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				mutex.release();
			}
			inicio++;
		}
	}

	private void calculo() {
		if (id % 3 == 1) {
			tempo = (int)((Math.random() * 801) + 200);
		} else if (id % 3 == 2) {
			tempo = (int)((Math.random() * 1001) + 500);
		} else if (id % 3 == 0) {
			tempo = (int)((Math.random() * 1001) + 1000);
		}
		
		try {
			sleep(tempo);
			System.out.println("#"+id+" fez um cálculo de "+tempo+"ms.");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void transacao() {
		if (id % 3 == 1) {
			try {
				sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		if (id % 3 == 2 || id % 3 == 0) {
			try {
				sleep(1500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("#"+id+" Transação de BD");
	}

	
}
