<?php

namespace Container0SpGFPD;

use Symfony\Component\DependencyInjection\Argument\RewindableGenerator;
use Symfony\Component\DependencyInjection\Exception\RuntimeException;

/**
 * @internal This class has been auto-generated by the Symfony Dependency Injection Component.
 */
class get_ServiceLocator__WbzsKmService extends App_KernelDevDebugContainer
{
    /**
     * Gets the private '.service_locator..WbzsKm' shared service.
     *
     * @return \Symfony\Component\DependencyInjection\ServiceLocator
     */
    public static function do($container, $lazyLoad = true)
    {
        return $container->privates['.service_locator..WbzsKm'] = new \Symfony\Component\DependencyInjection\Argument\ServiceLocator($container->getService, [
            'encoder' => ['services', 'security.password_encoder', 'getSecurity_PasswordEncoderService', true],
            'userRepository' => ['privates', 'App\\Repository\\UserRepository', 'getUserRepositoryService', true],
        ], [
            'encoder' => '?',
            'userRepository' => 'App\\Repository\\UserRepository',
        ]);
    }
}
