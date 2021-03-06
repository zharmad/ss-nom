package data.hullmods.base;
import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.CampaignFleetAPI;
import com.fs.starfarer.api.campaign.StarSystemAPI;
import com.fs.starfarer.api.characters.PersonAPI;
import com.fs.starfarer.api.fleet.FleetMemberAPI;
import com.fs.starfarer.api.combat.BaseHullMod;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;

public abstract class BaseFleetEffectHullMod extends BaseHullMod
{
	public CampaignFleetAPI findFleet( FleetMemberAPI member )
	{
		Map data = Global.getSector().getPersistentData();
		final String memo_key = "nom.data.hullmods.base.BaseFleetEffectHullMod.memo";
		Hashtable memo = (Hashtable) data.get( memo_key );
		if( memo == null )
		{
			memo = new Hashtable();
			data.put( memo_key, memo );
		}
		
		// ships change fleets infrequently; it's probably going to be in the same fleet
		PersonAPI commander = member.getFleetCommander();
		CampaignFleetAPI last_fleet_found = (CampaignFleetAPI)memo.get( member );
		if( last_fleet_found != null && last_fleet_found.getCommander() == commander )
			return last_fleet_found;
		
		// search all fleets for the commander
		for( Iterator star_system_i = Global.getSector().getStarSystems().iterator(); star_system_i.hasNext(); )
		{
			for( Iterator fleet_i = ((StarSystemAPI)star_system_i.next()).getFleets().iterator(); fleet_i.hasNext(); )
			{
				CampaignFleetAPI fleet = (CampaignFleetAPI)fleet_i.next();
				if( commander == fleet.getCommander() )
				{
					memo.put( member, fleet );
					//_.L("found fleet by commander");
					return fleet;
				}
			}
		}
		return null;
	}	
}
