package data.console;

import java.lang.StringBuilder;
import java.util.LinkedList;
import java.util.List;
import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.CampaignFleetAPI;
import com.fs.starfarer.api.campaign.FactionAPI;
import com.fs.starfarer.api.campaign.LocationAPI;
import com.fs.starfarer.api.campaign.SectorAPI;
import com.fs.starfarer.api.fleet.FleetMemberAPI;
import java.util.HashMap;
import java.util.Map;
import org.lazywizard.console.BaseCommand;
import org.lazywizard.console.Console;


public class ListNomads implements BaseCommand{

  @Override
  public CommandResult runCommand(String args, CommandContext context) {
    if (context == CommandContext.CAMPAIGN_MAP) {
      //
      StringBuilder output = new StringBuilder();
      for (LocationAPI location : Global.getSector().getAllLocations()) {
        boolean pr_loc = false;
        for (CampaignFleetAPI fleet : location.getFleets()) {
          if ("nomads".equals(fleet.getFaction().getId())) {
            if (!pr_loc) {
              output
                .append("  ")
                .append( location.getName() )
                .append("\n");
              pr_loc = true;
            }
            output
              .append(  "        ")
              .append( fleet.getNameWithFactionKeepCase() )
              .append( " (").append( fleet.getFleetPoints() ).append(" fp)")
              .append("\n");
          }
        }
      }
      Console.showMessage(output.toString());
      //
      return CommandResult.SUCCESS;
    } else {
      return CommandResult.WRONG_CONTEXT;
    }
  }
  
}
