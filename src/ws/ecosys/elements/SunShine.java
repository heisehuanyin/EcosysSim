package ws.ecosys.elements;

public class SunShine implements Element {
	/**
	 * 太阳直射位置X
	 */
	private final int x_sun;
	/**
	 * 太阳直射位置y
	 */
	private final int y_sun;
	/**
	 * 行总数
	 */
	private final int rows;
	/**
	 * 列总数
	 */
	private final int cols;

	/**
	 * 阳光直射模块预设太阳能量总数
	 */
	public static final int SUNSHINE_NUMBER = 200000000;

	/**
	 * 新建一个{@link SunShine},用于计算日光能量
	 * 
	 * @param rows
	 *            行总数
	 * @param cols
	 *            列总数
	 * @param rowNum
	 *            太阳直射点行序
	 * @param colNum
	 *            太阳直射点列序
	 */
	public SunShine(final int rows, final int cols, final int rowNum, final int colNum) {
		this.x_sun = rowNum;
		this.y_sun = colNum;
		this.rows = rows;
		this.cols = cols;
	}

	/**
	 * 获取当地光照能量理论值
	 * 
	 * @param rowNum
	 *            本地行序号
	 * @param colNum
	 *            本地列序号
	 * @return 光照能量
	 */
	public double getSunnyPower(int rowNum, int colNum) {
		int x_step = Math.abs(rowNum - this.x_sun);
		int y_step = Math.abs(colNum - this.y_sun);
		
		if(y_step > cols/2)
			y_step = cols - y_step;
		
		if(x_step > (rows/2) || y_step > (cols/4))
			return 0;
		
		double avg_row = this.SUNSHINE_NUMBER / (rows / 2);
		double shineAtDirectRow = this.SUNSHINE_NUMBER - x_step * avg_row;
		
		double avg_col = shineAtDirectRow / (cols / 4);
		double realShine = shineAtDirectRow - y_step * avg_col;

		return realShine;
	}
}
