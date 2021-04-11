package it.polito.tdp.regine.model;

import java.util.ArrayList;
import java.util.List;

public class Regine {
	
	public List<Integer> risolvi(Integer N){
		List<Integer> risultato = new ArrayList<Integer>(N);
		this.cerca(new ArrayList<Integer>(), 0, N, risultato);
		return risultato;
	}

	// N è il numero di righe e colonne della scacchiera
	//   (righe e colonne numerate da 0 a N-1)
	// ad ogni livello posizioniamo una regina in una nuova riga
	
	// soluzione parziale: lista delle colonne in cui mettere le regine (prime righe)
	// 		List<Integer>
	// livello = quante righe sono già piene
	// livello = 0 => nessuna riga piena (devo mettere la regina nella riga 0)
	// livello = 3 => 3 righe piene (0, 1, 2), devo mettere la regina nella riga 3
	// [0]
	//     [0, 2]
	//            [0, 2, 1]
	
	private void cerca(List<Integer> parziale, Integer livello, Integer N, List<Integer> risultato) {
		if(livello==N) {
			// caso terminale
			risultato.addAll(parziale);
		} else {
			for(int colonna=0; colonna<N; colonna++) {
				if(livello!=0) {
					// if la mossa nella casella [livello][colonna] è valida
					// se sì, aggiungi a parziale e fai ricorsione
					boolean flag = true;
					
					for(int i=0; i<livello; i++) {
						if(parziale.get(i)==colonna)
							flag = false;
					}
					
					if(flag){
						for(int i=0; i<livello; i++) {
							for(int j=1; j<=livello; j++) {
								if(!flag)
									break;
								else if((parziale.get(i)+j==colonna && i+j==livello) || (parziale.get(i)-j==colonna && i+j==livello)) {
									flag=false;
									break;
								}
							}
						}
					}
					if (flag) {
						List<Integer> nuovoParziale = new ArrayList<>(N);
						nuovoParziale.addAll(parziale);
						nuovoParziale.add(colonna);
						cerca(nuovoParziale, livello + 1, N, risultato);
					}
				}
				else {
					List<Integer> nuovoParziale = new ArrayList<>(N);
					nuovoParziale.add(colonna);
					cerca(nuovoParziale, livello + 1, N, risultato);
				}
			}
		}
	}
	
	
	
}
