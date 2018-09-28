package ws.ecosys.area;

public class SquareUnit {
	/**
	 * 升温系数 -- 预设为水，受到地表环境的影响，需要进行后期运算
	 */
	private double tempratureTimes = 0.00000025;
	/**
	 * 热量逸散率，系统能能量向系统外逸散系数,不受环境影响
	 */
	private final double hotFalloutPercent;
	/**
	 * 能量转存储量，吸收的热量转入存储用途
	 */
	private final double hotTransCapacity;
	/**
	 * 物理温度下限,绝对零度为基准，是一个Unsigned数字
	 */
	private final double tempratureDownLimit = 0;
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
	private double hotHold = 0.0;
	

	/**
	 * 新建空间块，传入特定关键参数
	 * @param height 地面高度
	 * @param waterDepth 地面水深
	 * @param hotFalloutPercent 定温物体热量逸散系数
	 * @param hotTransCapacity 能量转存储数量
	 */
	public SquareUnit(int height, int waterDepth, final double hotFalloutPercent, final double hotTransCapacity) {
		this.height = height;
		this.depth = waterDepth;
		this.hotFalloutPercent = hotFalloutPercent;
		this.hotTransCapacity = hotTransCapacity;
	}

	/**
	 * 设置当前阳光能量,阳光是一个瞬时量
	 * @param d 阳光能量
	 */
	public void setSunnyPower(double d) {
		if(this.temprature <= this.tempratureDownLimit)
			this.temprature = this.tempratureDownLimit + 0.00001;
		
		
		if(d > this.hotTransCapacity) {
			double powerStored = d -  Math.sqrt(d / this.hotTransCapacity) * this.hotTransCapacity;
			this.hotHold += powerStored;
			d -= powerStored;
			
		}else if(d < this.hotTransCapacity){
			
			
			
			
			
			
			
			
			
			
			double feedBack = (this.hotHold * (this.hotTransCapacity - d))/(this.hotTransCapacity + this.hotHold);
			
			double xtemp = this.hotHold - feedBack;
			if(xtemp > 0 ) {
				d += feedBack;
				this.hotHold -= feedBack;
			}else {
				d += this.hotHold;
				this.hotHold = 0;
			}
			
			
			
			
		}
		
		this.temprature =( d*this.calcTempratureTimes() + (1-0.5*this.hotFalloutPercent)*this.temprature )
							/ (1 + 0.5*this.hotFalloutPercent);
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
