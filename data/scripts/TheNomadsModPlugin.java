package data.scripts;
import com.fs.starfarer.api.BaseModPlugin;
import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.SectorAPI;
import data.scripts.misc.Utils;
import data.scripts.world.systems.TheNomadsNur;
import data.scripts.plugins.TheNomadsCombatEnginePlugin;
import data.scripts.plugins.TheNomadsRetributionWeaponPlugin;

public class TheNomadsModPlugin extends BaseModPlugin
{
	@Override
	public void onNewGame()
	{
		init();
	}
	
	//@Override
	//public void onGameLoad()
	//{
	//  // does not work
	//	init();
	//}
	
	private void init()
	{
		if( Utils.can_be_loaded( "data.scripts.world.ExerelinGen" ))
			return;
		
		// normal stand-alone mode
		SectorAPI sector = Global.getSector();
		new TheNomadsNur().generate( sector );
	}
	
}
