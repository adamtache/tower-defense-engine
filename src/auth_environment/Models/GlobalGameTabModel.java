package auth_environment.Models;

import auth_environment.Models.Interfaces.IGlobalGameTabModel;
import game_data.AuthSerializer;
import game_data.GameData;
import game_engine.IAuthEnvironment;

/**
 * Created by BrianLin on 4/19/16
 * Team member responsible: Brian
 *
 * This Tab is for customizing Global Game settings + Saving/Loading. Will need the entire IEngineWorkspace passed
 * in so that it can be saved/loaded. 
 */

// TODO: check that loading GameData is reflected at AuthViewModel level (one level above this Model) 

public class GlobalGameTabModel implements IGlobalGameTabModel {
	
	private IAuthEnvironment myAuthData;  
	
	// TODO: are type arguments necessary? 
	private AuthSerializer writer = new AuthSerializer();

	public GlobalGameTabModel(IAuthEnvironment auth) {
		this.myAuthData = auth;  
	}

	@Override
	public void saveToFile() {
		writer.saveElement( (GameData) this.myAuthData); 
	}

	@Override
	public void loadFromFile() {
		// TODO: add error checking
		this.myAuthData = (IAuthEnvironment) writer.loadElement();
	}

	@Override
	public void setGameName(String name) {
		this.myAuthData.setGameName(name);
	}

	@Override
	public String getGameName() {
		return this.myAuthData.getGameName();
	}

	@Override
	public void setSplashFile(String name) {
		this.myAuthData.setSplashScreen(name);
	}

	@Override
	public String getSplashFile() {
		return this.myAuthData.getSplashScreen();
	}
}
