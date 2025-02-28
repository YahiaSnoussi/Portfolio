<?php

namespace App\Form;

use App\Entity\Participation;
use App\Entity\Seance;
use Symfony\Bridge\Doctrine\Form\Type\EntityType;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;

class ParticipationType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options)
    {
        $builder
            ->add('seance', EntityType::class, [
                'class' => Seance::class,
                'choice_label' => "titreseance",
                'placeholder' => "Choisir une séance",
                'multiple' => false,
                'mapped' => false,
                'required' => false,
            ])
            ->add('idclient')
            ->add('idcoach')
            ->add('status')
            ->add('idroutine');
    }

    public function configureOptions(OptionsResolver $resolver)
    {
        $resolver->setDefaults([
            'data_class' => Participation::class,
        ]);
    }
}
