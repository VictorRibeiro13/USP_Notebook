// Esqueleto da classe na qual devem ser implementadas as novas funcionalidades de desenho

public class ImageEx extends Image {

	public ImageEx(int w, int h, int r, int g, int b) {
		super(w, h, r, g, b);
	}

	public ImageEx(int w, int h){
		super(w, h);
	}

	public int distanceBetweenTwoPoints(int px, int py, int qx, int qy) {
		return (int) Math.round(Math.sqrt(Math.pow(qx - px, 2) + Math.pow(qy - py, 2)));
	}

	public int linearCombination(int p, int q, double alpha) {
		if (alpha < 0 || alpha > 1.0) throw new Error("Alpha is not in 0 <= alpha <= 1");
		return (int) Math.round(((1 - alpha) * p) + (alpha * q));
	}

	/**
	 *
	 * @param px x do primeiro ponto da reta
	 * @param py y do primeiro ponto da reta
	 * @param qx x do ultimo ponto da reta
	 * @param qy y do ultimo ponto da reta
	 * @param l limitante da curva
	 *
	 * 	Desenha na imagem a curva de Koch
	 *
	 * 	Para essa função, entedemos que os limites passados são validos,
	 * 	conforme e-mail explicado pelo professor.
	 *
	 */
	public void kochCurve(int px, int py, int qx, int qy, int l) {
		if (distanceBetweenTwoPoints(px, py, qx, qy) < l) {
			drawLine(px, py, qx, qy);
		} else {
			// Point A
			int ax = linearCombination(px, qx, 1.0 / 3.0);
			int ay = linearCombination(py, qy, 1.0 / 3.0);

			// Point C
			int cx = linearCombination(px, qx, 2.0 / 3.0);
			int cy = linearCombination(py, qy, 2.0 / 3.0);

			// m = medium point between P and Q
			int mx = linearCombination(px, qx, 1.0 / 2.0);
			int my = linearCombination(py, qy, 1.0 / 2.0);

			// u = triangle height
			int ux = (int) (-((qy - py)) * Math.sqrt(3) / 6.0);
			int uy = (int) ((qx - px) * Math.sqrt(3) / 6.0);

			// Point B
			int bx = (int) (mx - ux);
			int by = (int) (my - uy);

			// call the other function recursively
			kochCurve(cx, cy, qx, qy, l);
			kochCurve(px, py, ax, ay, l);
			kochCurve(bx, by, cx, cy, l);
			kochCurve(ax, ay, bx, by, l);
		}
	}

	public void regionFill(int x, int y, int reference_rgb) {
		if (x >= 0 && y >= 0 && x < getWidth() && y < getHeight() && getPixel(x, y) == reference_rgb) {
			setPixel(x, y);
			regionFill(x+1, y, reference_rgb);
			regionFill(x-1, y, reference_rgb);
			regionFill(x, y+1, reference_rgb);
			regionFill(x, y-1, reference_rgb);
		}
	}
}
