package ws.ecosys.sim;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ws.ecosys.area.SquareUnit;
import ws.ecosys.elements.SunShine;

public class Ecosys {
	private final int rowsCount;
	private final int colsCount;
	private List<List<SquareUnit>> map = new ArrayList<>();

	public Ecosys(final int rowsCount) {
		this.rowsCount = rowsCount;
		this.colsCount = this.rowsCount * 2;
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
				SquareUnit p = new SquareUnit(0, 0, 0.2, 50000000);
				x.add(p);
			}
		}
	}

	public void globalRefreshOnce(SunShine sun) {
		for (int i = 0; i < rowsCount; ++i) {
			List<SquareUnit> row = map.get(i);
			List<SquareUnit> next_row = i==(rowsCount-1) ? null : map.get(i + 1);
			
			for (int j = 0; j < colsCount; ++j) {
				SquareUnit target = row.get(j);
				double x = sun.getSunnyPower(i, j);
				target.setSunnyPower(x);
				
				SquareUnit next_atRow = row.get(j == (colsCount - 1) ? 0 : j + 1);
				target.InteractWithNext(next_atRow);
				
				if(next_row != null) {
					SquareUnit next_atCol = next_row.get(j);
					target.InteractWithNext(next_atCol);
				}
			}
		}
	}

	public void __printMap(BufferedWriter out) {
		for (List<SquareUnit> row : map) {
			for (SquareUnit x : row) {
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
		Ecosys core = new Ecosys(1000);
		core.LoadData();

		int rows = core.getRowCount();
		int cols = core.getColCount();

		int rTimes = 1;
		for (int a = 0; a < rTimes * cols; ++a) {
			SunShine sun = new SunShine(rows, cols, rows / 2, a % cols);
			core.globalRefreshOnce(sun);
			System.out.println("太阳位于：（" + rows / 2 + "," + a % cols + ")");
		}

		BufferedWriter outPort;
		try {
			outPort = new BufferedWriter(new FileWriter("./testdata/log.csv"));
			core.__printMap(outPort);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Work out!===================");
	}

}
