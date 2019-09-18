package ticketmachine;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TicketMachineTest {
	private static final int PRICE = 50; // Une constante

	private TicketMachine machine; // l'objet à tester

	@Before
	public void setUp() {
		machine = new TicketMachine(PRICE); // On initialise l'objet à tester
	}

	@Test
	// On vérifie que le prix affiché correspond au paramètre passé lors de l'initialisation
	// S1 : le prix affiché correspond à l’initialisation
	public void priceIsCorrectlyInitialized() {
		// Paramètres : message si erreur, valeur attendue, valeur réelle
		assertEquals("Initialisation incorrecte du prix", PRICE, machine.getPrice());
	}

	@Test
	// S2 : la balance change quand on insère de l’argent
	public void insertMoneyChangesBalance() {
		machine.insertMoney(10);
		machine.insertMoney(20);
		assertEquals("La balance n'est pas correctement mise à jour", 10 + 20, machine.getBalance()); // Les montants ont été correctement additionnés               
	}
        @Test 
        // S3:Le ticket n'imprime pas si le montant est insuffisant 
        public void dontprintticket(){
                machine.insertMoney(10);
                machine.insertMoney(20);
                assertFalse("La machine imprime un ticket meme si le montant est insuffisant",machine.printTicket());
        }
        
        @Test
         // S4: On imprime le ticket si montant est suffisant
        public void TestprintTicket(){
            machine.insertMoney(50);
           
            assertTrue("La machine n'imprime  pas un ticket meme si le montant est suffisant",machine.printTicket());
        }
        @Test
        //S5:Quand on imprime un ticket la balance est decrement du prix du ticket
        
        public void Testdecrement(){
            machine.insertMoney(10);
            machine.insertMoney(20);
            machine.insertMoney(50);
            machine.printTicket();
            assertEquals("La balance n'est pas  decrementer du prix du tocket",80-50,machine.getBalance());
            
        }
        
        @Test
        //S6:Le montant est coolecte est mis a jour quand on imprime un ticker (pas avant)
        public void printchangetotal(){
            machine.insertMoney(PRICE +1);
            int montant=machine.getTotal();
            machine.printTicket();
            assertEquals("le montant change apres l'avoir imprimer",montant+PRICE,machine.getTotal());
            
        }
        
        @Test
        //S7: refund() rend correctement la monnaie
        public void  refundrendmonnaie(){
            machine.insertMoney(PRICE +1);
            machine.printTicket();
            assertEquals("Refund rend bien la monnaie",1,machine.refund());
            
            
        }
        
        @Test
        //s8 refund remet la balance a 0
        public void refundremetazerobalance(){
            machine.insertMoney(PRICE +1);
            machine.printTicket();
            machine.refund();
            assertEquals("Refund remet pas  la balance a 0",0,machine.getBalance());
        }
        
        @Test
        //S9 on ne peut pas insérer un motant négatif
        public void dontinsertnegatifmontant(){
            machine.insertMoney(-2);
            assertEquals("Peut insérer des montants negatif",0,machine.getTotal());
            
        }
        @Test  (expected=IllegalArgumentException.class)
        //S10 on ne peut pas créer de machine qui delivre des tickets dont le prix est négatif 
        public void deleivrepasticketnegatif(){
            TicketMachine t_machine=new TicketMachine(-20);
        }
}
