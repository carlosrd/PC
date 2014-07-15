import java.util.concurrent.ThreadLocalRandom;


public class EscritorReentrante extends Thread {

		private MonitorArbitraje monitorArb;
		private BaseDatos baseDatos;

		EscritorReentrante(MonitorArbitraje monitor, BaseDatos BD, String nombre) {
			super(nombre);
			monitorArb = monitor;
			baseDatos = BD;
		}

		public void run() {
			try {
				while (true) {
					monitorArb.entrarEscribir();
					sleep(ThreadLocalRandom.current().nextInt(1500,2500));
					monitorArb.entrarEscribir();
					
					baseDatos.escribir();
					System.out.println(getName() + " escribe su nombre en la BD.");
					
					monitorArb.salirEscribir();
					monitorArb.salirEscribir();
					sleep(ThreadLocalRandom.current().nextInt(500,5000));
					
				}
			} catch (InterruptedException e){}
		}
		
	}
