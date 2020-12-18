package it.unipr.ingegneria.entities;

import it.unipr.ingegneria.api.IObserver;
import it.unipr.ingegneria.api.IStoreManager;
import it.unipr.ingegneria.api.IUserManager;

import java.io.Serializable;

public abstract class AbstractShop implements IUserManager, IStoreManager<Wine>, IObserver, Serializable  {
}
