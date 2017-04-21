package hu.bme.mit.spaceship;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class GT4500Test {

    private GT4500 ship;
    private TorpedoStore mockTSPrimary;
    private TorpedoStore mockTSSecondary;

    @Before
    public void init() {
        mockTSPrimary = mock(TorpedoStore.class);
        mockTSSecondary = mock(TorpedoStore.class);
        this.ship = new GT4500(mockTSPrimary, mockTSSecondary);
    }

    @Test
    public void fireTorpedos_Single_Success() {
        // Arrange
        when(mockTSPrimary.isEmpty()).thenReturn(false);
        when(mockTSPrimary.fire(1)).thenReturn(true);
        when(mockTSSecondary.isEmpty()).thenReturn(false);
        when(mockTSSecondary.fire(1)).thenReturn(true);

        // Act
        boolean result = ship.fireTorpedos(FiringMode.SINGLE);

        // Assert
        assertEquals(true, result);
    }

    @Test
    public void fireTorpedos_Single_Primary_Empty() {
        // Arrange
        when(mockTSPrimary.isEmpty()).thenReturn(true);
        when(mockTSSecondary.isEmpty()).thenReturn(false);
        when(mockTSSecondary.fire(1)).thenReturn(true);

        // Act
        boolean result = ship.fireTorpedos(FiringMode.SINGLE);

        // Assert
        assertEquals(true, result);
    }

    @Test
    public void fireTorpedos_Single_Secondary_Empty() {
        // Arrange
        when(mockTSPrimary.isEmpty()).thenReturn(false);
        when(mockTSPrimary.fire(1)).thenReturn(true);
        when(mockTSSecondary.isEmpty()).thenReturn(true);

        // Act
        boolean result = ship.fireTorpedos(FiringMode.SINGLE);

        // Assert
        assertEquals(true, result);
    }

    @Test
    public void fireTorpedos_Single_Both_Empty() {
        // Arrange
        when(mockTSPrimary.isEmpty()).thenReturn(true);
        when(mockTSSecondary.isEmpty()).thenReturn(true);

        // Act
        boolean result = ship.fireTorpedos(FiringMode.SINGLE);

        // Assert
        assertEquals(false, result);
    }

    @Test
    public void fireTorpedos_Single_Failure() {
        // Arrange
        when(mockTSPrimary.isEmpty()).thenReturn(false);
        when(mockTSPrimary.fire(1)).thenReturn(false);
        when(mockTSSecondary.isEmpty()).thenReturn(false);
        when(mockTSSecondary.fire(1)).thenReturn(false);

        // Act
        boolean result = ship.fireTorpedos(FiringMode.SINGLE);

        // Assert
        assertEquals(false, result);
    }

    @Test
    public void fireTorpedos_All_Success() {
        // Arrange
        when(mockTSPrimary.isEmpty()).thenReturn(false);
        when(mockTSPrimary.fire(1)).thenReturn(true);
        when(mockTSSecondary.isEmpty()).thenReturn(false);
        when(mockTSSecondary.fire(1)).thenReturn(true);

        // Act
        boolean result = ship.fireTorpedos(FiringMode.ALL);

        // Assert
        assertEquals(true, result);
    }

    @Test
    public void fireTorpedos_All_Primary_Failure() {
        // Arrange
        when(mockTSPrimary.isEmpty()).thenReturn(false);
        when(mockTSPrimary.fire(1)).thenReturn(false);
        when(mockTSSecondary.isEmpty()).thenReturn(false);
        when(mockTSSecondary.fire(1)).thenReturn(true);

        // Act
        boolean result = ship.fireTorpedos(FiringMode.ALL);

        // Assert
        assertEquals(true, result);
    }


    @Test
    public void fireTorpedos_All_Secondary_Failure() {
        // Arrange
        when(mockTSPrimary.isEmpty()).thenReturn(false);
        when(mockTSPrimary.fire(1)).thenReturn(true);
        when(mockTSSecondary.isEmpty()).thenReturn(false);
        when(mockTSSecondary.fire(1)).thenReturn(false);

        // Act
        boolean result = ship.fireTorpedos(FiringMode.ALL);

        // Assert
        assertEquals(true, result);
    }


    @Test
    public void fireTorpedos_All_Both_Failure() {
        // Arrange
        when(mockTSPrimary.isEmpty()).thenReturn(false);
        when(mockTSPrimary.fire(1)).thenReturn(false);
        when(mockTSSecondary.isEmpty()).thenReturn(false);
        when(mockTSSecondary.fire(1)).thenReturn(false);

        // Act
        boolean result = ship.fireTorpedos(FiringMode.ALL);

        // Assert
        assertEquals(false, result);

        verify(mockTSPrimary, times(1)).fire(1);
        verify(mockTSPrimary, times(1)).fire(1);
    }

    @Test
    public void fireTorpedos_All_Primary_Empty_Secondary_Success() {
        // Arrange
        when(mockTSPrimary.isEmpty()).thenReturn(true);
        when(mockTSSecondary.isEmpty()).thenReturn(false);
        when(mockTSSecondary.fire(1)).thenReturn(true);

        // Act
        boolean result = ship.fireTorpedos(FiringMode.ALL);

        // Assert
        assertEquals(true, result);
    }

    @Test
    public void fireTorpedos_All_Primary_Empty_Secondary_Fail() {
        // Arrange
        when(mockTSPrimary.isEmpty()).thenReturn(true);
        when(mockTSSecondary.isEmpty()).thenReturn(false);
        when(mockTSSecondary.fire(1)).thenReturn(false);

        // Act
        boolean result = ship.fireTorpedos(FiringMode.ALL);

        // Assert
        assertEquals(false, result);
    }


    @Test
    public void fireTorpedos_All_Primary_Success_Secondary_Empty() {
        // Arrange
        when(mockTSPrimary.isEmpty()).thenReturn(false);
        when(mockTSPrimary.fire(1)).thenReturn(true);
        when(mockTSSecondary.isEmpty()).thenReturn(true);

        // Act
        boolean result = ship.fireTorpedos(FiringMode.ALL);

        // Assert
        assertEquals(true, result);
    }


    @Test
    public void fireTorpedos_All_Primary_Failure_Secondary_Empty() {
        // Arrange
        when(mockTSPrimary.isEmpty()).thenReturn(false);
        when(mockTSPrimary.fire(1)).thenReturn(false);
        when(mockTSSecondary.isEmpty()).thenReturn(true);

        // Act
        boolean result = ship.fireTorpedos(FiringMode.ALL);

        // Assert
        assertEquals(false, result);
    }

    @Test
    public void fireTorpedos_All_Both_Empty() {
        // Arrange
        when(mockTSPrimary.isEmpty()).thenReturn(true);
        when(mockTSSecondary.isEmpty()).thenReturn(true);

        // Act
        boolean result = ship.fireTorpedos(FiringMode.ALL);

        // Assert
        assertEquals(false, result);
    }

    @Test
    public void fireTorpedos_Single_Success_2() {
        // Arrange
        when(mockTSPrimary.isEmpty()).thenReturn(false);
        when(mockTSPrimary.fire(1)).thenReturn(true);
        when(mockTSSecondary.isEmpty()).thenReturn(false);
        when(mockTSSecondary.fire(1)).thenReturn(true);

        // Act
        boolean result = ship.fireTorpedos(FiringMode.SINGLE);

        // Assert
        assertEquals(true, result);

        // Act
        result = ship.fireTorpedos(FiringMode.SINGLE);

        // Assert
        assertEquals(true, result);
    }

    @Test
    public void fireTorpedos_Single_Primary_Empty_2() {
        // Arrange
        when(mockTSPrimary.isEmpty()).thenReturn(true);
        when(mockTSSecondary.isEmpty()).thenReturn(false);
        when(mockTSSecondary.fire(1)).thenReturn(true);

        // Act
        boolean result = ship.fireTorpedos(FiringMode.SINGLE);

        // Assert
        assertEquals(true, result);

        // Act
        result = ship.fireTorpedos(FiringMode.SINGLE);

        // Assert
        assertEquals(true, result);
    }

    @Test
    public void fireTorpedos_Single_Secondary_Empty_2() {
        // Arrange
        when(mockTSPrimary.isEmpty()).thenReturn(false);
        when(mockTSPrimary.fire(1)).thenReturn(true);
        when(mockTSSecondary.isEmpty()).thenReturn(true);

        // Act
        boolean result = ship.fireTorpedos(FiringMode.SINGLE);

        // Assert
        assertEquals(true, result);

        // Act
        result = ship.fireTorpedos(FiringMode.SINGLE);

        // Assert
        assertEquals(true, result);
    }

    @Test
    public void fireTorpedos_Single_Primary_Last_Round_Secondary_Empty() {
        // Arrange
        when(mockTSPrimary.isEmpty()).thenReturn(false);
        when(mockTSPrimary.fire(1)).thenReturn(true);
        when(mockTSSecondary.isEmpty()).thenReturn(true);

        // Act
        boolean result = ship.fireTorpedos(FiringMode.SINGLE);

        // Assert
        assertEquals(true, result);

        // Arrange
        when(mockTSPrimary.isEmpty()).thenReturn(true);

        // Act
        result = ship.fireTorpedos(FiringMode.SINGLE);

        // Assert
        assertEquals(false, result);
    }
}
