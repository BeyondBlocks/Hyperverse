//
// Hyperverse - A Minecraft world management plugin
//
// This program is free software: you can redistribute it and/or modify
// it under the terms of the GNU General Public License as published by
// the Free Software Foundation, either version 3 of the License, or
// (at your option) any later version.
//
// This program is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// GNU General Public License for more details.
//
// You should have received a copy of the GNU General Public License
// along with this program. If not, see <http://www.gnu.org/licenses/>.
//

package se.hyperver.hyperverse.features.external;

import com.earth2me.essentials.Essentials;
import com.earth2me.essentials.IEssentials;
import com.earth2me.essentials.utils.LocationUtil;
import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import se.hyperver.hyperverse.Hyperverse;
import se.hyperver.hyperverse.features.PluginFeature;
import se.hyperver.hyperverse.service.internal.SafeTeleportService;

import java.util.Collections;

/**
 * Feature hooking into Essentials
 */
public class EssentialsFeature extends PluginFeature {

    private IEssentials essentials;

    @Override public void initializeFeature() {
        JavaPlugin.getPlugin(Hyperverse.class).getLogger().info("Using Essentials to provide safe-teleportation lookup.");
        Hyperverse.getApi().getServicePipeline().registerServiceImplementation(SafeTeleportService.class,
            new EssentialsSafeTeleportService(), Collections.emptyList());
        this.essentials = JavaPlugin.getPlugin(Essentials.class);
    }

    private class EssentialsSafeTeleportService implements SafeTeleportService {

        @Nullable @Override public Location handle(@NotNull final Location location) {
            try {
                return LocationUtil.getSafeDestination(essentials, location);
            } catch (final Exception ignored) {
            }
            return null;
        }

    }

}
