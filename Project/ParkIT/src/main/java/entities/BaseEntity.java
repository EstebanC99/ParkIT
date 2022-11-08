package entities;

public abstract class BaseEntity {
	private int ID;
	
	private String Descripcion;
	
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public void setID(String string) {
		int id = 0;
		try {
			id = Integer.parseInt(string);
		}
		catch (NumberFormatException ex) {
			id = 0;
		}
		finally {
			this.setID(id);
		}
	}
	
	public String getDescripcion() {
		return Descripcion.toUpperCase();
	}
	public void setDescripcion(String description) {
		Descripcion = description.toUpperCase();
	}

}
