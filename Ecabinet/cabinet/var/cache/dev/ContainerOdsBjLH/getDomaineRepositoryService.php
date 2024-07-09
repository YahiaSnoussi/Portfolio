<?php

namespace ContainerOdsBjLH;

use Symfony\Component\DependencyInjection\Argument\RewindableGenerator;
use Symfony\Component\DependencyInjection\Exception\RuntimeException;

/**
 * @internal This class has been auto-generated by the Symfony Dependency Injection Component.
 */
class getDomaineRepositoryService extends App_KernelDevDebugContainer
{
    /**
     * Gets the private 'App\Repository\DomaineRepository' shared autowired service.
     *
     * @return \App\Repository\DomaineRepository
     */
    public static function do($container, $lazyLoad = true)
    {
        return $container->privates['App\\Repository\\DomaineRepository'] = new \App\Repository\DomaineRepository(($container->services['doctrine'] ?? $container->getDoctrineService()));
    }
}
