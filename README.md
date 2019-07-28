# react-native-turn-off-optimization

## Getting started

`$ npm install react-native-turn-off-optimization --save`

### Installation

`$ react-native link react-native-turn-off-optimization`

## Usage
```javascript
import RNTurnOffOptimization from 'react-native-turn-off-optimization';

RNTurnOffOptimization.isOptimizationTurnedOn()
    .then(isTurnedOn => {
        if (isTurnedOn) {
            RNTurnOffOptimization.openTurnOffOptimizationModal();
        } 
    })
```