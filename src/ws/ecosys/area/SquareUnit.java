package ws.ecosys.area;

import java.util.Stack;

public class SquareUnit {
	/**
	 * 升温系数 -- 预设为水，受到地表环境的影响，需要进行后期运算
	 */
	private final double tempratureTimes = 1/4200;
	/**
	 * 热量逸散系数，系统能能量向系统外逸散系数,不受环境影响
	 */
	private final double hotFalloutPercent;
	/**
	 * 能量转存储百分比
	 */
	//private final double hotTransPercent;
	/**
	 * 物理温度下限
	 */
	private final double tempratureDownLimit = -300;
	/**
	 * 温度是一个状态量，初始温度为温度下限
	 */
	private double temprature = this.tempratureDownLimit;
	/**
	 * 高度是一个状态值
	 */
	private int height = 0;
	/**
	 * 水深是一个状态值
	 */
	private int depth = 0;
	/**
	 * 热量存储容器，模拟环境和物体吸能过程，热量由表及里渗透，再由内至外缓慢释放
	 */
	//private double hotHold = 0.0;
	

	/**
	 * 新建空间块，传入特定关键参数
	 * @param height 地面高度
	 * @param waterDepth 地面水深
	 * @param hotTransPercent 温度转存储百分比
	 * @param hotFalloutPercent 定温物体热量逸散系数
	 */
	public SquareUnit(int height, int waterDepth, final double hotFalloutPercent) {
		this.height = height;
		this.depth = waterDepth;
		this.hotFalloutPercent = hotFalloutPercent;
	}

	/**
	 * 设置当前阳光能量,阳光是一个瞬时量
	 * @param d 阳光能量
	 */
	public void setSunnyPower(double d) {
		if(this.temprature <= this.tempratureDownLimit)
			this.temprature = this.tempratureDownLimit + 0.00001;
		
		this.temprature += d*this.calcTempratureTimes();
		this.temprature = (this.temprature - this.tempratureDownLimit) * this.hotFalloutPercent / 2 + this.tempratureDownLimit;
		
	}
	
	/**
	 * 计算地表升温系数，其乘以能量则计算定量能量能够升温程度
	 * @return 升温系数
	 */
	private double calcTempratureTimes() {
		return this.tempratureTimes;
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
