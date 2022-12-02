package org.matsim.project;

import com.google.inject.AbstractModule;
import com.google.inject.Module;
import org.matsim.core.config.Config;
import org.matsim.core.config.ConfigUtils;
import org.matsim.core.controler.Injector;
import org.matsim.core.controler.OutputDirectoryHierarchy;

class Run31Oct22b {


    public static void main(String[] args) {
        Config config = ConfigUtils.loadConfig( "scenarios/equil/config.xml" );
        config.controler().setOutputDirectory( "ouput/equil/" );
        config.controler().setOverwriteFileSetting( OutputDirectoryHierarchy.OverwriteFileSetting.deleteDirectoryIfExists );
        config.controler().setLastIteration( 2 );

        // use the matsim Module!
        Module module  = new org.matsim.core.controler.AbstractModule() {
            @Override // auto-generated
            public void install() {
                bind( Abc.class ).to( AbcImpl.class );
            }
        };

        com.google.inject.Injector injector = Injector.createInjector( config, module );

        Abc abc = injector.getInstance( Abc.class );
        abc.doSomething();

    }

    interface Abc { // manually added - my own interface...
        void doSomething();
    }

    private static class AbcImpl implements Abc {

        @Override public void doSomething(){
            System.out.println("executing doSomething of AbcImpl");
        }

    }


}