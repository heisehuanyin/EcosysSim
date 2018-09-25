package ws.ecosys.area;

public class SquareUnit {
	/**
	 * 升温系数
	 */
	private final double tempratureRisingTimes;
	/**
	 * 温度是一个状态量
	 */
	private double temprature = 0;
	/**
	 * 降温系数
	 */
	private final double tempratureDownTimes;
	/**
	 * 物理温度下限
	 */
	private final double tempratureDownLimit = -1000;
	/**
	 * 高度是一个状态值
	 */
	private int height = 0;
	/**
	 * 水深是一个状态值
	 */
	private int depth = 0;
	

	/**
	 * 新建空间块，传入特定关键参数
	 * @param height 土地高度
	 * @param waterDepth 地表水深度
	 */
	public SquareUnit(int height, int waterDepth, final double risingTimes, final double downTimes) {
		this.height = height;
		this.depth = waterDepth;
		this.tempratureRisingTimes = risingTimes;
		this.tempratureDownTimes = downTimes;
	}

	/**
	 * 设置当前阳光能量,阳光是一个瞬时量
	 * @param d 阳光能量
	 */
	public void setSunnyPower(double d) {
		if(this.temprature <= this.tempratureDownLimit)
			this.temprature = this.tempratureDownLimit + 0.001;
		
		double realTimeRisingNumber = this.tempratureRisingTimes/ (this.temprature - this.tempratureDownLimit);
		this.temprature += d * realTimeRisingNumber;
		
		double realTimeDownNumber = this.tempratureDownTimes * (this.temprature - this.tempratureDownLimit);
		this.temprature -= d * realTimeDownNumber;
		
	}



	public double getTemprature() {
		return this.temprature;
	}

	public int getLandHeight() {
		return this.height;
	}

	public int getWaterDeepth() {
		return this.depth;
	}

	public void InteractWithNext(SquareUnit next) {

	}
	
	public String getDescriptionString() {
		return "";
	}
}
