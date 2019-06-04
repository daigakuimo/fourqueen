<?php

    $ga = new geneticAlgorithm();

    $ga->init();
    $ga->main();

    //model
    class fourQueen{
        public $position;
        public $fitness;

        public function __construct($q1,$q2,$q3,$q4){
            $position = array($q1,$q2,$q3,$q4);
        }

    }

    //controller
    class geneticAlgorithm{

        public function main(){

            $this::evaluation();

            while(terminationCondition()){

                $this::selection();

                $this::crossover();

                $this::mutation();

                $this::evaluation();

            }

        }

        public function init(){

        }

        public function selection(){

        }

        public function crossover(){

        }

        public function mutation(){

        }

        public function evaluation(){

        }

        public function terminationCondition(){

        }

    }

?>