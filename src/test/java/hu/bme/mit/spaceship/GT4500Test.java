package hu.bme.mit.spaceship;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class GT4500Test {

  private TorpedoStore pTS;
  private TorpedoStore sTS;
  private GT4500 ship;

  @Before
  public void init(){
    pTS = mock(TorpedoStore.class);
    sTS = mock(TorpedoStore.class);
    this.ship = new GT4500(pTS,sTS);
  }

  @Test
  public void fireTorpedos_Single_Success(){
    // Arrange
      when(pTS.isEmpty()).thenReturn(false);
      when(sTS.isEmpty()).thenReturn(false);
      when(pTS.fire(1)).thenReturn(true);
      when(sTS.fire(1)).thenReturn(true);
    // Act
    boolean result = ship.fireTorpedos(FiringMode.SINGLE);

    // Assert
    assertEquals(true, result);
    verify(pTS,times(1)).fire(1);
  }

  @Test
  public void fireTorpedos_All_Success(){
    // Arrange
      when(pTS.isEmpty()).thenReturn(false);
      when(sTS.isEmpty()).thenReturn(false);
      when(pTS.fire(1)).thenReturn(true);
      when(sTS.fire(1)).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedos(FiringMode.ALL);

    // Assert
    assertEquals(true, result);
      verify(pTS,times(1)).fire(1);
      verify(sTS,times(1)).fire(1);
  }

  @Test
    public void fireTorpedos_2_Single_Fire()
  {
      // Arrange
      when(pTS.isEmpty()).thenReturn(false);
      when(sTS.isEmpty()).thenReturn(false);
      when(pTS.fire(1)).thenReturn(true);
      when(sTS.fire(1)).thenReturn(true);
      // Act
      boolean result = ship.fireTorpedos(FiringMode.SINGLE);
      result = result & ship.fireTorpedos(FiringMode.SINGLE);

      // Assert
      assertEquals(true, result);
      verify(pTS,times(1)).fire(1);
      verify(sTS,times(1)).fire(1);
  }
    @Test
    public void fireTorpedos_3_Single_Fire()
    {
        // Arrange
        when(pTS.isEmpty()).thenReturn(false);
        when(sTS.isEmpty()).thenReturn(false);
        when(pTS.fire(1)).thenReturn(true);
        when(sTS.fire(1)).thenReturn(true);
        // Act
        boolean result = ship.fireTorpedos(FiringMode.SINGLE);
        result = result & ship.fireTorpedos(FiringMode.SINGLE);
        result = result & ship.fireTorpedos(FiringMode.SINGLE);

        // Assert
        assertEquals(true, result);
        verify(pTS,times(2)).fire(1);
        verify(sTS,times(1)).fire(1);
    }

    @Test
    public void fireTorpedos_SINGLE_Primary_Empty()
    {
        // Arrange
        when(pTS.isEmpty()).thenReturn(true);
        when(sTS.isEmpty()).thenReturn(false);
        when(pTS.fire(1)).thenReturn(false);
        when(sTS.fire(1)).thenReturn(true);
        // Act
        boolean result = ship.fireTorpedos(FiringMode.SINGLE);

        // Assert
        assertEquals(true, result);
        verify(pTS,times(1)).isEmpty();
        verify(pTS,times(0)).fire(1);
        verify(sTS,times(1)).fire(1);
    }

    @Test
    public void fireTorpedos_SINGLE_Secondary_Twice()
    {
        // Arrange
        when(pTS.isEmpty()).thenReturn(true);
        when(sTS.isEmpty()).thenReturn(false);
        when(pTS.fire(1)).thenReturn(false);
        when(sTS.fire(1)).thenReturn(true);
        // Act
        boolean result = ship.fireTorpedos(FiringMode.SINGLE);
        result = result & ship.fireTorpedos(FiringMode.SINGLE);

        // Assert
        assertEquals(true, result);
        verify(pTS,times(2)).isEmpty();
        verify(pTS,times(0)).fire(1);
        verify(sTS,times(2)).fire(1);
    }

    @Test
    public void fireTorpedos_SINGLE_Primary_Twice()
    {
        // Arrange
        when(pTS.isEmpty()).thenReturn(false);
        when(sTS.isEmpty()).thenReturn(true);
        when(pTS.fire(1)).thenReturn(true);
        when(sTS.fire(1)).thenReturn(false);
        // Act
        boolean result = ship.fireTorpedos(FiringMode.SINGLE);
        result = result & ship.fireTorpedos(FiringMode.SINGLE);

        // Assert
        assertEquals(true, result);
        verify(pTS,times(2)).isEmpty();
        verify(sTS,times(1)).isEmpty();
        verify(pTS,times(2)).fire(1);
        verify(sTS,times(0)).fire(1);
    }

    @Test
    public void fireTorpedos_fire_laser()
    {
        // Arrange

        // Act
        boolean result = ship.fireLasers(FiringMode.SINGLE);

        // Assert
        assertEquals(false, result);
    }

    @Test
    public void fireTorpedos_All_PrimaryWasFired(){
        // Arrange
        when(pTS.isEmpty()).thenReturn(false);
        when(sTS.isEmpty()).thenReturn(false);
        when(pTS.fire(1)).thenReturn(true);
        when(sTS.fire(1)).thenReturn(true);

        // Act
        boolean result = ship.fireTorpedos(FiringMode.SINGLE);
        result = result & ship.fireTorpedos(FiringMode.ALL);

        // Assert
        assertEquals(true, result);
        verify(pTS,times(2)).fire(1);
        verify(sTS,times(1)).fire(1);
    }


}
