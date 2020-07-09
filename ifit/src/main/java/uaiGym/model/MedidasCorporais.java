package uaiGym.model;

public class MedidasCorporais {

	private float altura;
	private float peso;
	private float gorduraPercentual;
	private float residuosPercentual;
	private float musculoPercentual;

	public MedidasCorporais(float altura, float peso, float gorduraPercentual, float residuosPercentual,
			float musculoPercentual) {
		this.altura = altura;
		this.peso = peso;
		this.gorduraPercentual = gorduraPercentual;
		this.residuosPercentual = residuosPercentual;
		this.musculoPercentual = musculoPercentual;
	}

	public float getAltura() {
		return altura;
	}

	public float getPeso() {
		return peso;
	}

	public float getGorduraPercentual() {
		return gorduraPercentual;
	}

	public float getResiduosPercentual() {
		return residuosPercentual;
	}

	public float getMusculoPercentual() {
		return musculoPercentual;
	}

	public float getIMC() {
		return peso / (altura * altura);
	}
}
