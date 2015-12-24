package me.ryanhamshire.GriefPrevention.command;

import me.ryanhamshire.GriefPrevention.Claim;
import me.ryanhamshire.GriefPrevention.GriefPrevention;
import me.ryanhamshire.GriefPrevention.Messages;
import me.ryanhamshire.GriefPrevention.TextMode;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.text.Texts;

import java.util.ArrayList;
import java.util.List;

public class CommandAdminClaimsList implements CommandExecutor {

    @Override
    public CommandResult execute(CommandSource src, CommandContext ctx) {
        // find admin claims
        List<Claim> claims = new ArrayList<>();
        for (Claim claim : GriefPrevention.instance.dataStore.claims) {
            if (claim.ownerID == null) { // admin claim
                claims.add(claim);
            }
        }

        if (claims.size() > 0) {
            GriefPrevention.sendMessage(src, TextMode.Instr, Messages.ClaimsListHeader);
            for (int i = 0; i < claims.size(); i++) {
                Claim claim = claims.get(i);
                GriefPrevention.sendMessage(src, Texts.of(TextMode.Instr, GriefPrevention.getfriendlyLocationString(claim.getLesserBoundaryCorner())));
            }
        }

        return CommandResult.success();
    }
}