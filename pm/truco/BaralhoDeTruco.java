package pm.truco;

import java.util.*;

public class BaralhoDeTruco extends Baralho {
	private static BaralhoDeTruco instance = null;
	private String[] naipesValidos = {"paus",
		"ouros",
		"copas",
		"espadas"};

	private char[] numerosValidos = {'2', '3', '4', '5', '6', '7',
		'Q', 'J', 'K', 'A'};
	private Stack<Carta> cartas;
	private Hashtable<Carta, Integer> valores;

	private int getValorManilha(Carta c) {
		if (c.getNumero() == '4' && c.getNaipe().equals("paus")) {
			return 13;
		} else if (c.getNumero() == '7' && c.getNaipe().equals("copas")) {
			return 12;
		} else if (c.getNumero() == 'a' && c.getNaipe().equals("espadas")) {
			return 11;
		} else if (c.getNumero() == '7' && c.getNaipe().equals("ouros")) {
			return 10;
		}
		return -1;
	}

	private void inicializarValores() {
		this.valores = new Hashtable<Carta, Integer>();
		char[] ordem = {'4', '5', '6', '7', 'q', 'j', 'k', 'a', '2', '3'};

		for (Carta c: this.cartas) {
			int valorManilha = this.getValorManilha(c);
			if (valorManilha > -1) {
				this.valores.put(c, valorManilha);
				continue;
			}

			for (int i = 0; i < ordem.length; i++) {
				if (c.getNumero() == ordem[i]) {
					this.valores.put(c, i);
					break;
				}
			}
		}
	}

	public ArrayList<Carta> retirarCartas(int n) {
		ArrayList<Carta> retiradas = new ArrayList<Carta>();

		for (int i = 0; i < n; i++) {
			retiradas.add(this.cartas.pop());
		}

		return retiradas;
	}

	private void inicializarCartas() {
		this.cartas = new Stack<Carta>();
		for (int i = 0; i < this.numerosValidos.length; i++) {
			for (int j = 0; j < this.naipesValidos.length; j++) {
				this.cartas.push(new Carta(this.numerosValidos[i], this.naipesValidos[j]));
			}
		}

		Collections.shuffle(this.cartas);
	}
	private BaralhoDeTruco() {
		this.inicializarCartas();
		this.inicializarValores();
	}

	public int getValorCarta(Carta c) {
		return this.valores.get(c);
	}

	public static BaralhoDeTruco getInstance() {
		if (instance == null) {
			instance = new BaralhoDeTruco();
		}

		return instance;
	}
}
