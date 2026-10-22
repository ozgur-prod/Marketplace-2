package pgdp.saleuine3;

import java.util.ArrayList;
import java.util.List;

import pgdp.pingulib.datastructures.trees.BST;

public class SaleuinBusiness {
	private BST<MarketOrder> orderDB;
	private List<ArrayList<Integer>> bestelluinIDLists;
	private List<ArrayList<Integer>> generatorIDLists;

	private int ordersPerBestelluin;
	private int supplyRunsPerGenerator;
	private int maxGeneratorOutput;

	public SaleuinBusiness(List<ArrayList<Integer>> bestelluinIDLists, List<ArrayList<Integer>> generatorIDLists,
			int ordersPerBestelluin, int supplyRunsPerGenerator, int maxGeneratorOutput) {
		this.orderDB = new BST<>();
		this.bestelluinIDLists = bestelluinIDLists;
		this.generatorIDLists = generatorIDLists;
		this.ordersPerBestelluin = ordersPerBestelluin;
		this.supplyRunsPerGenerator = supplyRunsPerGenerator;
		this.maxGeneratorOutput = maxGeneratorOutput;
	}

	public BST<MarketOrder> getOrderDB() {
		return orderDB;
	}

	public void runBusiness() {

		for (int i = 1; i <= bestelluinIDLists.size(); i++) {

			String name = "Bestelluin " + i;
			Bestelluin bestelluin = new Bestelluin(bestelluinIDLists.get(i-1),ordersPerBestelluin,orderDB,name);
			Thread thread = new Thread(bestelluin);
			thread.start();

		}

		for (int j = 1; j <= generatorIDLists.size(); j++){

			String name = "Generator " + j;
			PinguFoodGenerator generator = new PinguFoodGenerator(maxGeneratorOutput,supplyRunsPerGenerator,orderDB,generatorIDLists.get(j-1),name);
			Thread thread = new Thread(generator);
			thread.start();

		}
		
	}
}
