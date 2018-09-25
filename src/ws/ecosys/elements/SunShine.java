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
	public static final int SUNSHINE_NUMBER = 20000;

	/**
	 * 新建一个{@link SunShine},用于计算日光能量
	 * 
	 * @param rows
	 *            行总数
	 * @param cols
	 *            列总数
	 * @param x
	 *            太阳直射点横轴
	 * @param y
	 *            太阳直射点纵轴
	 */
	public SunShine(final int rows, final int cols, final int x, final int y) {
		this.x_sun = x;
		this.y_sun = y;
		this.rows = rows;
		this.cols = cols;
	}

	/**
	 * 获取当地光照能量理论值
	 * 
	 * @param x
	 *            本地横轴坐标
	 * @param y
	 *            本地纵轴坐标
	 * @return 光照能量
	 */
	public double getSunnyPower(int x, int y) {
		int x_step = Math.abs(x - this.x_sun);
		int y_step = Math.abs(y - this.y_sun);
		
		if(x_step > (rows/4) || y_step > (cols/4))
			return 0;
		
		double avg_x = this.SUNSHINE_NUMBER / (rows / 4);
		double avg_y = avg_x / (cols / 4);

		double realShine = this.SUNSHINE_NUMBER - x_step * avg_x - y_step * avg_y;

		return realShine;
	}
}
