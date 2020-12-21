package it.unipr.ingegneria.views.forms;
/**
 * The {@code IForm} is the interface that contain the principal operation of the form views
 * @author Ruslan Vasyunin, Francesca Rossi, Everton Ejike
 */
public interface IForm<T, C>{
     T  getGrid(C c);

}
