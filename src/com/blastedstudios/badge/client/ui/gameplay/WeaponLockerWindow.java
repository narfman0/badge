package com.blastedstudios.badge.client.ui.gameplay;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.blastedstudios.badge.network.Generated.Gun;
import com.blastedstudios.badge.util.EventEnum;
import com.blastedstudios.badge.util.EventManager;
import com.blastedstudios.badge.world.GunFactory;

public class WeaponLockerWindow extends Window {
	public WeaponLockerWindow(final Skin skin){
		super("Weapon Locker", skin);
		Table table = new Table(skin);
		for(final Gun gun : GunFactory.getStockGuns()){
			table.add(new GunTable(skin, gun));
			Button buyButton = new TextButton("Buy", skin);
			table.add(buyButton);
			buyButton.addListener(new ClickListener() {
				@Override public void clicked(InputEvent event, float x, float y) {
					EventManager.sendEvent(EventEnum.CHARACTER_GUN_BUY_REQUEST, gun);
				}
			});
			table.row();
		}
		Button exitButton = new TextButton("Exit", skin);
		exitButton.addListener(new ClickListener() {
			@Override public void clicked(InputEvent event, float x, float y) {
				event.getListenerActor().getParent().remove();
			}
		});
		add(new ScrollPane(table));
		row();
		add(exitButton);
		pack();
		setX(Gdx.graphics.getWidth()/2 - getWidth()/2);
		setY(Gdx.graphics.getHeight()/2 - getHeight()/2);
	}
}
