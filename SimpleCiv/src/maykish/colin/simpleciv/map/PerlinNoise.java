package maykish.colin.simpleciv.map;

import java.util.Random;

public class PerlinNoise {

	public static double[][] GenerateWhiteNoise(int width, int height) {
		Random r = new Random();
		double[][] noise = new double[width][height];

		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				noise[i][j] = r.nextDouble();
			}
		}

		return noise;
	}

	public static double[][] GenerateSmoothNoise(int width, int height, int octave) {
		double[][] baseNoise = GenerateWhiteNoise(width, height);

		double[][] smoothNoise = new double[width][height];

		int samplePeriod = (int) Math.pow(2.0, octave);
		double sampleFrequency = 1.0 / samplePeriod;

		for (int i = 0; i < width; i++) {
			int sample_i0 = (i / samplePeriod) * samplePeriod;
			int sample_i1 = (sample_i0 + samplePeriod) % width;
			double horizontal_blend = (i - sample_i0) * sampleFrequency;

			for (int j = 0; j < height; j++) {
				int sample_j0 = (j / samplePeriod) * samplePeriod;
				int sample_j1 = (sample_j0 + samplePeriod) % height;
				double vertical_blend = (j - sample_j0) * sampleFrequency;

				double top = Interpolate(baseNoise[sample_i0][sample_j0],
						baseNoise[sample_i1][sample_j0], horizontal_blend);

				double bottom = Interpolate(baseNoise[sample_i0][sample_j1],
						baseNoise[sample_i1][sample_j1], horizontal_blend);

				smoothNoise[i][j] = Interpolate(top, bottom, vertical_blend);
			}
		}
		return smoothNoise;
	}

	private static double Interpolate(double x0, double x1, double alpha) {
		return x0 * (1 - alpha) + alpha * x1;
	}

	public static double[][] GeneratePerlinNoise(int width, int height, int octaveCount) {

		double[][][] smoothNoise = new double[octaveCount][][];

		double persistance = 0.7;

		for (int i = 0; i < octaveCount; i++) {
			smoothNoise[i] = GenerateSmoothNoise(width, height, i);
		}

		double[][] perlinNoise = new double[width][height];
		double amplitude = 1.0;
		double totalAmplitude = 0.0;

		for (int o = octaveCount - 1; o >= 0; o--) {
			amplitude *= persistance;
			totalAmplitude += amplitude;

			for (int i = 0; i < width; i++) {
				for (int j = 0; j < height; j++) {
					perlinNoise[i][j] += smoothNoise[o][i][j] * amplitude;
				}
			}
		}

		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				perlinNoise[i][j] /= totalAmplitude;
			}
		}

		return perlinNoise;

	}
}
