// Esqueleto da classe na qual devem ser implementadas as novas funcionalidades de desenho

public class ImageEx extends Image {

	public ImageEx(int w, int h, int r, int g, int b){
		super(w, h, r, g, b);
	}

	public ImageEx(int w, int h){
		super(w, h);
	}

	public double distanceBetweenTwoPoints(int px, int py, int qx, int qy) {
		return Math.sqrt(Math.pow(qx - px, 2) + Math.pow(qy - py, 2));
	}

	public int[] linearCombination(int px, int py, int qx, int qy, double alpha) {
		int[] xy = new int[2];




		//return (int) ((1 - alpha) * (p + alpha) * q);
	}

	public void kochCurve(int px, int py, int qx, int qy, int l) {
		// setting default color
		this.setBgColor(0, 0, 0);
		this.clear();

		// drawing initial line
		if (c < l) this.drawLine(px, py, qx, qy);

		int[] a = linearCombination(px, qx, 1/3);
		int ay = linearCombination(py, qy, 1/3);

		int cx = linearCombination(px, qx, 2/3);
		int cy = linearCombination(py, qy, 2/3);

		int mx = linearCombination(px, qx, 1/2);
		int my = linearCombination(py, qy, 1/2);



	}

	public void regionFill(int x, int y, int reference_rgb){

	}
}
