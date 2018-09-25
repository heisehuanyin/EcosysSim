package ws.ecosys.sim;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ws.ecosys.area.SquareUnit;
import ws.ecosys.elements.SunShine;

public class Ecosys {
	private int rowsCount = 2000;
	private int colsCount = 2000;
	private List<List<SquareUnit>> map = new ArrayList<>();

	public Ecosys() {
	}

	public int getRowCount() {
		return this.rowsCount;
	}

	public int getColCount() {
		return this.colsCount;
	}

	public void LoadData() {

		for (int i = 0; i < rowsCount; ++i) {
			ArrayList<SquareUnit> x = new ArrayList<SquareUnit>();
			map.add(x);

			for (int j = 0; j < colsCount; ++j) {
				SquareUnit p = new SquareUnit(0, 0, 0.5, 0.5);
				x.add(p);
			}
		}
	}

	public void globalRefreshOnce(SunShine sun) {
		for (int i = 0; i < rowsCount; ++i) {
			for (int j = 0; j < colsCount; ++j) {
				SquareUnit target = map.get(i).get(j);
				SquareUnit next_atRow = map.get(i).get(j == (colsCount - 1) ? 0 : j + 1);
				SquareUnit next_atCol = map.get(i == (rowsCount - 1) ? 0 : i + 1).get(j);

				target.setSunnyPower(sun.getSunnyPower(i, j));

				target.InteractWithNext(next_atRow);
				target.InteractWithNext(next_atCol);
			}
		}
	}
	
	public void __printMap(BufferedWriter out) {
		for(List<SquareUnit> row:map) {
			for(SquareUnit x : row) {
				try {
					out.write(x.getTemprature() + ",");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			try {
				out.newLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		Ecosys core = new Ecosys();
		core.LoadData();
		
		int rows = core.getRowCount();
		int cols = core.getColCount();

		for (int a = 0; a < 10; ++a) {
			SunShine sun = new SunShine(rows, cols, a%rows, a%cols);
			core.globalRefreshOnce(sun);
		}
		
		BufferedWriter outPort;
		try {
			outPort = new BufferedWriter(new FileWriter("./testdata/log.txt"));
			core.__printMap(outPort);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Work out!===================");
	}

}
