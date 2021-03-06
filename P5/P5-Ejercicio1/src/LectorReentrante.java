import java.util.concurrent.ThreadLocalRandom;


public class LectorReentrante extends Thread {

		private MonitorArbitraje monitorArb;
		private BaseDatos baseDatos;
		
		LectorReentrante(MonitorArbitraje monitor, BaseDatos BD, String nombre) {
			super(nombre);
			monitorArb = monitor;
			baseDatos = BD;
		}

		public void run() {
			try {
				while (true) {
					monitorArb.entrarLeer();
					sleep(ThreadLocalRandom.current().nextInt(1000,2000));
					monitorArb.entrarLeer();
					
					System.out.println(getName() + " lee lo siguiente de la BD: " + baseDatos.leer());
					
					monitorArb.salirLeer();
					sleep(ThreadLocalRandom.current().nextInt(500,5000));
					monitorArb.salirLeer();
				}
			} catch (InterruptedException e){System.err.println("Interrupted while sleeping");}
		}
		
	}
