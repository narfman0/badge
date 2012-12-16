package com.blastedstudios.badge.client.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.blastedstudios.badge.Badge;
import com.blastedstudios.badge.client.Network;
import com.blastedstudios.badge.network.Generated.NetAccount;
import com.blastedstudios.badge.ui.AbstractScreen;
import com.blastedstudios.badge.util.EventEnum;
import com.blastedstudios.badge.util.EventManager;
import com.blastedstudios.badge.util.EventManager.EventListener;

public class MainScreen extends AbstractScreen<Badge> {
	public MainScreen(final Badge game){
		super(game);
		new Network();
		final TextField usernameLabel = new TextField("", skin);
		usernameLabel.setMessageText("<username>");
		final TextField passwordLabel = new TextField("", skin);
		passwordLabel.setMessageText("<password>");
		final Button loginButton = new TextButton("Login", skin);
		final Button exitButton = new TextButton("Exit", skin);
		loginButton.addListener(new ClickListener() {
			@Override public void clicked(InputEvent event, float x, float y) {
				loginButton.setTouchable(Touchable.disabled);
				EventManager.sendEvent(EventEnum.ACCOUNT_RETRIEVE_REQUEST, 
						usernameLabel.getText(), passwordLabel.getText());
				EventManager.addListener(EventEnum.ACCOUNT_RETRIEVE_RESPONSE, new LoginSuccessListener());
			}
		});
		exitButton.addListener(new ClickListener() {
			@Override public void clicked(InputEvent event, float x, float y) {
				Gdx.app.exit();
			}
		});
		Window window = new Window("Badge", skin);
		window.add(usernameLabel);
		window.row();
		window.add(passwordLabel);
		window.row();
		window.add(loginButton);
		window.row();
		window.add(exitButton);
		window.pack();
		window.setX(Gdx.graphics.getWidth()/2 - window.getWidth()/2);
		window.setY(Gdx.graphics.getHeight()/2 - window.getHeight()/2);
		stage.addActor(window);
	}
	
	class LoginSuccessListener implements EventListener{
		@Override public void handleEvent(EventEnum event, Object... data) {
			switch(event){
			case ACCOUNT_RETRIEVE_RESPONSE:
				EventManager.removeListener(EventEnum.ACCOUNT_RETRIEVE_RESPONSE, this);
				game.setScreen(new AccountScreen(game, (NetAccount)data[0]));
				break;
			default:
				break;
			}
		}
	}
}
